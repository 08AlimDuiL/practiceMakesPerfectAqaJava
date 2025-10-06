package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.ContactsData;
import ru.stqa.ptf.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();
        app.logout();
    }

    @Test(dependsOnMethods = "testGroupCreation")
    public void testCreationContact() throws Exception {
        app.goToAddNewContact();
        app.getContactHelper().fillFormContact(new ContactsData(
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
                "2000",
                "test1")
        );
        app.goToHomePage();
        app.logout();

    }
}