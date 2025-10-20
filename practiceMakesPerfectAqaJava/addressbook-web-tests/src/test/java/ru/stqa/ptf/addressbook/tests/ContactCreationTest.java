package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().goToHomeHeader();
    }

    @Test(enabled = false)
    public void testCreationContact() {
        int before = app.contact().count();
        System.out.println("Groups before: " + before);
        app.contact().create(new ContactData(
                        "Vasilii",
                        "Ivanovich",
                        "Ivanov",
                        "Vasya",
                        "Neo",
                        "address",
                        "1112233",
                        "+79113334455",
                        "2223344",
                        "no",
                        "1112233@mail.ru",
                        "2",
                        "January",
                        "2222",
                        "test1"),
                true);
        app.goToHomePage();
        int after = app.contact().count();
        System.out.println("Groups after: " + after);

        Assert.assertEquals(after, before + 1);
    }

    @Test
    public void testCreationContactList() {
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData("Vasilii", "Ivanov");
        app.contact().create(contact, true);
        app.goToHomePage();
        List<ContactData> after = app.contact().list();

        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }

    @Test
    public void testCreationContactSetHamcrest() {
        Contacts before = app.contact().getAll();
        ContactData contact = new ContactData().withFirstName("Dmitrii").withLastName("Ivanov");
        app.contact().create(contact, true);
        app.goToHomePage();
        Contacts after = app.contact().getAll();

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) ->
                        g.getId()).max().getAsInt()))));
        assertThat(after.size(), equalTo(before.size() + 1));
    }

    @Test
    public void testCreationContactNewSetHamcrest() throws InterruptedException {
        Thread.sleep(1000);
        Set<ContactData> before = app.contact().all();
        System.out.println(before);
        ContactData contact = new ContactData().withFirstName("Dmitrii").withLastName("Ivanov");
        app.contact().create(contact, true);
        app.goToHomePage();
        Set<ContactData> after = app.contact().all();
        System.out.println(after);

        assertThat(after.size(), equalTo(before.size() + 1));

        int maxId = after
                .stream()
                .mapToInt(ContactData::getId)
                .max()
                .orElse(0);
        Set<ContactData> expected = new HashSet<>(before);
        expected.add(contact.withId(maxId));

        assertThat(after, equalTo(expected));
    }
}
