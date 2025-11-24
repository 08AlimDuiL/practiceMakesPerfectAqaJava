package ru.stqa.ptf.addressbook.folderSeven.tests.dbHW15And16.contacts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.folderSeven.model.ContactData;
import ru.stqa.ptf.addressbook.folderSeven.model.Contacts;
import ru.stqa.ptf.addressbook.folderSeven.tests.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HbContactDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().goToAddNewContactHeader();
            app.contact().create(new ContactData().withFirstName("Nikolai").withLastName("Petrov"), true);
        }
    }

    @Test(description = "task 15.6 ")
    public void testHbContactDeletion() {

        Contacts before = app.db().contacts();

        System.out.println("==================================================================");
        for (ContactData contact : before) {
            System.out.println(contact);
            System.out.println(contact.getGroups());
        }

        System.out.println("Total contacts before: " + before.size());

        ContactData deletedContact = before.iterator().next();
        app.goTo().goToHomeHeader();
        app.contact().delete(deletedContact);


        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();


        System.out.println("==================================================================");
        for (ContactData contact : after) {
            System.out.println(contact);
        }
        System.out.println("Total contacts after: " + after.size());

        assertThat(after, equalTo(before.withOut(deletedContact)));

        verifyGroupListInUi();
    }
}