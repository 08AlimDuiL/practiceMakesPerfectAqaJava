package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactsData;


public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().goToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().editContact();
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
                        "4444",
                        null),
                false
        );
        app.getContactHelper().updateContact();
    }
}
