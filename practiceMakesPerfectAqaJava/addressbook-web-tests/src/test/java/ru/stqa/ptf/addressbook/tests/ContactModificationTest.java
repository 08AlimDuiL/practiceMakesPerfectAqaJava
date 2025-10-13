package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactsData;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


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

    @Test
    public void testContactModificationList() {
        app.getNavigationHelper().goToHomeHeader();


        if (!app.getContactHelper().isThereAGroup()) {
            app.getContactHelper().createContact(new ContactsData("Petr", "Petrov"), true);
            app.getNavigationHelper().goToHomeHeader();
        }

        List<ContactsData> before = app.getContactHelper().getContactList();

        app.getContactHelper().selectContactByIndex(before.size() - 1);
        app.getContactHelper().editContact();

        ContactsData contact = new ContactsData(before.get(before.size() - 1).getId(), "Pavel", "Petrov");

        app.getContactHelper().fillFormContact(contact, false);
        app.getContactHelper().updateContact();
        // Thread.sleep(3000);
        app.getNavigationHelper().goToHomeHeader();

        List<ContactsData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);

        Comparator<? super ContactsData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());

        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
