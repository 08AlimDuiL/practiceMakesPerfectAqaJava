package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactsData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTest extends TestBase {

    @Test
    public void testCreationContact() {
        app.getNavigationHelper().goToHomeHeader();

        int before = app.getContactHelper().getContactCount();
        System.out.println("Groups before: " + before);

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
        app.goToHomePage();

        int after = app.getContactHelper().getContactCount();
        System.out.println("Groups after: " + after);

        Assert.assertEquals(after, before + 1);
        // app.logout();
    }

    @Test
    public void testCreationContactList() {
        app.getNavigationHelper().goToHomeHeader();

        List<ContactsData> before = app.getContactHelper().getContactList();

        ContactsData contact = new ContactsData("Vasilii", "Ivanov");
        app.getContactHelper().createContact(contact, true);
        app.goToHomePage();

        List<ContactsData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactsData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }
}