package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

import static ru.stqa.ptf.addressbook.tests.TestBase.app;

public class ContactPhoneTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().goToHomeHeader();
    }

    @Test
    public void testsContactPhones() throws InterruptedException {
        Thread.sleep(3000);
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
        Thread.sleep(3000);
        ContactData contact = app.contact().getAll().iterator().next();
        Thread.sleep(3000);
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    }
}
