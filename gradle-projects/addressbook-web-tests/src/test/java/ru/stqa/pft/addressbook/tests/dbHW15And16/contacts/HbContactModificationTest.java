package ru.stqa.pft.addressbook.tests.dbHW15And16.contacts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HbContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().goToAddNewContactHeader();
            app.contact().create(new ContactData().withFirstName("Nikolai").withLastName("Petrov"), true);
        }
    }

    @Test(description = "task 15.2 ")
    public void testHbContactModification() {
        String timestamp = String.valueOf(System.currentTimeMillis());

        Contacts before = app.db().contacts();

        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName("Vanya_" + timestamp)
                .withLastName("Smirnov_" + timestamp);
        app.goTo().goToHomeHeader();
        app.contact().modify(contact);
        app.goTo().goToHomeHeader();

        assertThat(app.contact().count(), equalTo(before.size()));

        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

        verifyGroupListInUi();
    }
}
