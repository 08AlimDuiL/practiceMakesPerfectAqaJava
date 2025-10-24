package ru.stqa.ptf.addressbookMySql.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.stqa.ptf.addressbookMySql.model.ContactData;
import ru.stqa.ptf.addressbookMySql.model.Contacts;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {

        super(wd);
    }

    public void initContactCreation() {

        wd.findElement(By.linkText("add new")).click();
    }

    public void selectContact() {

        click(By.name("selected[]"));
    }

    public void selectContactByIndex(int index) {

        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();

        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//div[@id='content']/div"), "Record successful deleted"
        ));
        contactsCache = null;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();

        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//div[@id='content']/div"), "Record successful deleted"
        ));
        contactsCache = null;
    }

    public void editContact() {

        click(By.xpath("//img[@alt='Edit']"));
    }

    public void enterContact() {
        click(By.xpath("//form/input[@value=\"Enter\"][last()]"));
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='msgbox']//a[contains(text(), 'home page')]")
        ));
        contactsCache = null;
    }

    public void updateContact() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//div[@class='msgbox']"), "Address book updated"
        ));
        contactsCache = null;
    }

    public void modify(ContactData contactData) {
        selectContactById(contactData.getId());
        editContact();
        fillFormContact(contactData, true);
        updateContact();
        contactsCache = null;
    }

    public void fillFormContact(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhoneNumber());
        type(By.name("mobile"), contactData.getMobilePhoneNumber());
        type(By.name("work"), contactData.getWorkPhoneNumber());
        type(By.name("fax"), contactData.getFax());
        type(By.name("email"), contactData.getEmail());
        clickDropdownAndType(By.name("bday"), contactData.getBirthDay());
        clickDropdownAndType(By.name("bmonth"), contactData.getBirthMonth());
        type(By.name("byear"), contactData.getBirthYear());
        if (creation) {
            clickDropdownAndType(By.name("new_group"), contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void fillFormContactWithFoto(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        attach(By.name("photo"), contactData.getPhoto());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhoneNumber());
        type(By.name("mobile"), contactData.getMobilePhoneNumber());
        type(By.name("work"), contactData.getWorkPhoneNumber());
        type(By.name("fax"), contactData.getFax());
        type(By.name("email"), contactData.getEmail());
        clickDropdownAndType(By.name("bday"), contactData.getBirthDay());
        clickDropdownAndType(By.name("bmonth"), contactData.getBirthMonth());
        type(By.name("byear"), contactData.getBirthYear());
        if (creation) {
            clickDropdownAndType(By.name("new_group"), contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public boolean isThereAGroup() {

        return isElementPresent(By.name("selected[]"));
    }

    public void create(ContactData contact, boolean creation) {
        initContactCreation();
        fillFormContact(contact, creation);
        enterContact();
        contactsCache = null;
    }

    public int count() {

        return wd.findElements(By.xpath("//td[@class=\"center\"]/input[@name=\"selected[]\"]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(id, firstName, lastName);
            contacts.add(contact);
        }

        return contacts;
    }

    private Contacts contactsCache = null;

    public Contacts getAll() {
        if (contactsCache != null) {
            return new Contacts(contactsCache);
        }
        contactsCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

            contactsCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }

        return new Contacts(contactsCache);
    }

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String[] phones = cells.get(5).getText().split("\n");
            String homePhone = "";
            String mobilePhone = "";
            String workPhone = "";

            if (phones.length > 0) {
                homePhone = phones[0];
            }
            if (phones.length > 1) {
                mobilePhone = phones[1];
            }
            if (phones.length > 2) {
                workPhone = phones[2];
            }
            contacts.add(new ContactData()
                    .withId(id)
                    .withFirstName(firstname)
                    .withLastName(lastname)
                    .withHomePhoneNumber(homePhone)
                    .withMobilePhoneNumber(mobilePhone)
                    .withWorkPhoneNumber(workPhone)
            );
        }
        return contacts;
    }


    public Set<ContactData> almostAll() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();

            contacts.add(new ContactData()
                    .withId(id)
                    .withFirstName(firstname)
                    .withLastName(lastname)
                    .withAddress(address)
                    .withAllEmails(allEmails)
                    .withAllPhones(allPhones)
            );
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String homePhoneNumber = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhoneNumber = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhoneNumber = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");

        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstName(firstName)
                .withLastName(lastName)
                .withHomePhoneNumber(homePhoneNumber)
                .withMobilePhoneNumber(mobilePhoneNumber)
                .withWorkPhoneNumber(workPhoneNumber)
                .withEmail(email)
                .withEmail2(email2)
                .withEmail3(email3)
                .withAddress(address);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id))); // метод последовательных приближений, узнали про String.format
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

        // wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
        // wd.findElement(By.xpath(String.format("//tr[./input[@value='%s']]/td[8]/a", id))).click();
        // wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }
}