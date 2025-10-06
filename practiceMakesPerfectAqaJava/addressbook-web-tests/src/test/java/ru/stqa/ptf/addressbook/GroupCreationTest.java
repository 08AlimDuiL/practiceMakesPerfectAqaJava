package ru.stqa.ptf.addressbook;

import org.testng.annotations.*;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        goToGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("test1", "test2", "test3"));
        submitGroupCreation();
        returnToGroupPage();
        logout();
    }

    @Test(dependsOnMethods = "testGroupCreation")
    public void testCreationContact() throws Exception {
        goToAddNewContact();
        fillFormContact(new ContactsData(
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
        goToHomePage();
        logout();
    }
}