package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.ptf.addressbook.model.ContactsData;

public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void goToHomePage() {
        click(By.linkText("home"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }
    public void editContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void updateContact() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void fillFormContact(ContactsData contactsData) {
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
        clickDropdownAndType(By.name("new_group"), contactsData.getGroup());
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillEditContact(ContactsData contactsData) {
        type(By.name("firstname"), contactsData.getName());
        type(By.name("middlename"), contactsData.getMiddleName());
        type(By.name("lastname"), contactsData.getLastName());
        type(By.name("nickname"), contactsData.getNickName());
        type(By.name("company"), contactsData.getCompany());
        type(By.name("address"), contactsData.getAddress());
        type(By.name("home"), contactsData.getHomePhoneNumber());
        type(By.name("mobile"), contactsData.getMobilePhoneNumber());
        type(By.name("fax"), contactsData.getFax());
        type(By.name("email"), contactsData.getEmail());
        clickDropdownAndType(By.name("bday"), contactsData.getBirthDay());
        clickDropdownAndType(By.name("bmonth"), contactsData.getBirthMonth());
        type(By.name("byear"), contactsData.getBirthYear());
    }
}
