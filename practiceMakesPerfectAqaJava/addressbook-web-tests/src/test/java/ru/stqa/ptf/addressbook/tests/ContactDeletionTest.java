package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactsData;

import java.util.List;

public class ContactDeletionTest extends TestBase {

    @Test(enabled = false)
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
        //Thread.sleep(3000);
        int before = app.getContactHelper().getContactCount();
        System.out.println("Groups before: " + before);

        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().goToHomeHeader();

        int after = app.getContactHelper().getContactCount();
        System.out.println("Groups after: " + after);

        Assert.assertEquals(after, before - 1);
    }

    @Test(enabled = false)
    public void testContactDeletionList() {
        app.getNavigationHelper().goToHomeHeader();
        if (!app.getContactHelper().isThereAGroup()) {
            app.getContactHelper().createContact(new ContactsData(
                            "Vasilii",
                            "Petrov"),
                    true);
            app.getNavigationHelper().goToHomeHeader();
        }

        List<ContactsData> before = app.getContactHelper().getContactList();

        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().goToHomeHeader();

        List<ContactsData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size() - 1);
    }
}
