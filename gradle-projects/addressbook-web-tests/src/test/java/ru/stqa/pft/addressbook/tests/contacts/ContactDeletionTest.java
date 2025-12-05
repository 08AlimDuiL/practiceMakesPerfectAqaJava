package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().goToHomeHeader();
    }

//    @Test(enabled = false)
//    public void testContactDeletion() throws InterruptedException {
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
//                            "2222",
//                            "test1"),
//                    true);
//            app.goTo().goToHomeHeader();
//        }
//        //Thread.sleep(3000);
//        int before = app.contact().count();
//        System.out.println("Groups before: " + before);
//        app.contact().selectContact();
//        app.contact().deleteContact();
//        app.goTo().goToHomeHeader();
//        int after = app.contact().count();
//        System.out.println("Groups after: " + after);
//
//        Assert.assertEquals(after, before - 1);
//    }
//
//    @Test
//    public void testContactDeletionList() {
//        if (!app.contact().isThereAGroup()) {
//            app.contact().create(new ContactData(
//                            "Vasilii",
//                            "Petrov"),
//                    true);
//            app.goTo().goToHomeHeader();
//        }
//        List<ContactData> before = app.contact().list();
//        app.contact().selectContact();
//        app.contact().deleteContact();
//        app.goTo().goToHomeHeader();
//        List<ContactData> after = app.contact().list();
//
//        Assert.assertEquals(after.size(), before.size() - 1);
//    }
//
//    @Test
//    public void testContactDeletionHamcrest() {
//        if (app.contact().list().size() == 0) {
//            app.contact().create(new ContactData().withFirstName("Nikolai").withLastName("Petrov"), true);
//            app.goTo().goToHomeHeader();
//        }
//        Contacts before = app.contact().getAll();
//        ContactData deletedContact = before.iterator().next();
//        app.contact().delete(deletedContact);
//        app.goTo().goToHomeHeader();
//        assertThat(app.contact().count(), equalTo(before.size() - 1));
//        Contacts after = app.contact().getAll();
//
//        assertThat(after, equalTo(before.withOut(deletedContact)));
//    }
}
