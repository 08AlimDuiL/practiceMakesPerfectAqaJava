package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.ptf.addressbook.model.ContactsData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
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
        type(By.name("bday"), contactsData.getBirthDay());
        type(By.name("bmonth"), contactsData.getBirthMonth());
        type(By.name("byear"), contactsData.getBirthYear());


        wd.findElement(By.name("new_group")).click();
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactsData.getGroup());
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();

    }
}
