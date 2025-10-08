package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactsData;


public class ContactCreationTest extends TestBase {

    @Test
    public void testCreationContact() {
        app.getContactHelper().createContact(new ContactsData(
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
                        "2222",
                        "test1"),
                true);
        //app.getContactHelper().enterContact();
       // app.goToHomePage();
        app.logout();
    }
}
