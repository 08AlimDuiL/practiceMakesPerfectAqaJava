package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.stqa.ptf.addressbook.model.ContactsData;

import java.time.Duration;

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

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();

        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//div[@id='content']/div"), "Record successful deleted"
        ));
    }

    public void editContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void enterContact() {
        // click(By.xpath("//div[@id='content']/form/input[21]"));
        click(By.xpath("//form/input[@value=\"Enter\"][last()]"));
    }

    public void updateContact() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void fillFormContact(ContactsData contactsData, boolean creation) {
        type(By.name("firstname"), contactsData.getName());
        type(By.name("middlename"), contactsData.getMiddleName());
        type(By.name("lastname"), contactsData.getLastName());
        type(By.name("nickname"), contactsData.getNickName());
        type(By.name("company"), contactsData.getCompany());
        type(By.name("address"), contactsData.getAddress());
        type(By.name("home"), contactsData.getHomePhoneNumber());
        type(By.name("mobile"), contactsData.getMobilePhoneNumber());
        type(By.name("work"), contactsData.getWork());
        type(By.name("fax"), contactsData.getFax());
        type(By.name("email"), contactsData.getEmail());
        clickDropdownAndType(By.name("bday"), contactsData.getBirthDay());
        clickDropdownAndType(By.name("bmonth"), contactsData.getBirthMonth());
        type(By.name("byear"), contactsData.getBirthYear());
        if (creation) {
            clickDropdownAndType(By.name("new_group"), contactsData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public boolean isThereAGroup() {

        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactsData contactsData, boolean creation) {
        initContactCreation();
        fillFormContact(contactsData, creation);
        enterContact();
    }

    public int getContactCount() {

        return wd.findElements(By.xpath("//td[@class=\"center\"]/input[@name=\"selected[]\"]")).size();
    }
}
