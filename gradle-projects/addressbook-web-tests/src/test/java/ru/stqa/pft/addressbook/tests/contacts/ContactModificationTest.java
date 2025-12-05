package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().goToHomeHeader();
    }

//    @Test(enabled = false)
//    public void testContactModification() {
//        int before = app.contact().count();
//        System.out.println("Groups before: " + before);
//        if (!app.contact().isThereAGroup()) {
//            app.contact().create(new ContactData(
//                            "Vasilii",
//                            "Ivanovich",
//                            "Ivanov",
//                            "Vasya",
//                            "Neo",
//                            "address",
//                            "1112233",
//                            "+79113334455",
//                            "2223344",
//                            "no",
//                            "1112233@mail.ru",
//                            "2",
//                            "January",
//                            "6666",
//                            "test1"),
//                    true);
//            app.goTo().goToHomeHeader();
//        }
//        app.contact().selectContact();
//        app.contact().editContact();
//        app.contact().fillFormContact(new ContactData(
//                        "Vasilii",
//                        "Ivanovich",
//                        "Ivanov",
//                        "Vasya",
//                        "Neo",
//                        "address",
//                        "1112233",
//                        "+79113334455",
//                        "2223344",
//                        "no",
//                        "1112233@mail.ru",
//                        "2",
//                        "January",
//                        "6554",
//                        null),
//                false
//        );
//        app.contact().updateContact();
//        app.goTo().goToHomeHeader();
//        int after = app.group().getGroupCount();
//        System.out.println("Groups after: " + after);
//        Assert.assertEquals(after, before);
//    }
//
//    @Test(enabled = false)
//    public void testContactModificationList() {
//        if (!app.contact().isThereAGroup()) {
//            app.contact().create(new ContactData("Petr", "Petrov"), true);
//            app.goTo().goToHomeHeader();
//        }
//        List<ContactData> before = app.contact().list();
//        app.contact().selectContactByIndex(before.size() - 1);
//        app.contact().editContact();
//        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Pavel", "Petrov");
//        app.contact().fillFormContact(contact, false);
//        app.contact().updateContact();
//        // Thread.sleep(3000);
//        app.goTo().goToHomeHeader();
//        List<ContactData> after = app.contact().list();
//
//        Assert.assertEquals(after.size(), before.size());
//
//        before.remove(before.size() - 1);
//        before.add(contact);
//        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//        before.sort(byId);
//        after.sort(byId);
//
//        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
//    }
//
//    @Test
//    public void testContactModificationSetHamcrest() {
//        if (app.contact().list().size() == 0) {
//            app.contact().create(new ContactData().withFirstName("Nik").withLastName("Petrov"), true);
//            app.goTo().goToHomeHeader();
//        }
//        Contacts before = app.contact().getAll();
//        ContactData modifiedContact = before.iterator().next();
//        ContactData contactData = new ContactData()
//                .withId(modifiedContact.getId())
//                .withFirstName("Nikolya")
//                .withLastName("Petrov");
//        app.contact().modify(contactData);
//        app.goTo().goToHomeHeader();
//
//        assertThat(app.contact().count(), equalTo(before.size()));
//
//        Contacts after = app.contact().getAll();
//
//        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contactData)));
//    }
}
