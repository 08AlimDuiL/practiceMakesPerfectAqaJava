package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactsData;


public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomeHeader();

        int before = app.getContactHelper().getContactCount();
        System.out.println("Groups before: " + before);

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
                            "6666",
                            "test1"),
                    true);
            app.getNavigationHelper().goToHomeHeader();
        }
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
                        "6554",
                        null),
                false
        );
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToHomeHeader();

        int after = app.getGroupHelper().getGroupCount();
        System.out.println("Groups after: " + after);

        Assert.assertEquals(after, before);
    }
}