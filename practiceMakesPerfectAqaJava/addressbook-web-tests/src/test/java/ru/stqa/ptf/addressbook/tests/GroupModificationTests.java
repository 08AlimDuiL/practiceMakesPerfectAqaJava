package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactsData;
import ru.stqa.ptf.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test5", "test5", "test5"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
    }

    @Test
    public void testContactModification() {
        app.getContactHelper().goToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().editContact();
        app.getContactHelper().fillEditContact(new ContactsData(
                "Vasilii",
                "Ivanovich",
                "Ivanov",
                "Vasya",
                "Neo",
                "address",
                "1112233",
                "+79113334455",
                "work",
                "no",
                "1112233@mail.ru",
                "2",
                "January",
                "3000",
                "test1"
        ));
        app.getContactHelper().updateContact();
    }
}
