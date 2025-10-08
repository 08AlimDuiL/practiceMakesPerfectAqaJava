package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactsData;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion() throws InterruptedException {
        app.getNavigationHelper().goToHomeHeader();
        if (!app.getContactHelper().isThereAGroup()) {
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
            app.getNavigationHelper().goToHomeHeader();
        }
        //  Thread.sleep(5000);
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
    }
}
