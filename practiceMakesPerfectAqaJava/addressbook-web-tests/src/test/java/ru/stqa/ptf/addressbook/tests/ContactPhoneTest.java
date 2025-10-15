package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().goToHomeHeader();
    }

    @Test
    public void testsContactPhones() throws InterruptedException {
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData()
                            .withFirstName("Nik")
                            .withLastName("Petrov")
                            .withHomePhoneNumber("111")
                            .withMobilePhoneNumber("222")
                            .withWorkPhoneNumber("3333"),
                    true);
            app.goTo().goToHomeHeader();
        }
        Thread.sleep(1000);
        ContactData contact = app.contact().all().iterator().next();
        Thread.sleep(1000);
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getHomePhoneNumber(), equalTo(cleaned(contactInfoFromEditForm.getHomePhoneNumber())));
        assertThat(contact.getMobilePhoneNumber(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhoneNumber())));
        assertThat(contact.getWorkPhoneNumber(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhoneNumber())));
    }

    public String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

}
