package ru.stqa.ptf.addressbook.folderSeven.tests.contacts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.folderSeven.model.ContactData;
import ru.stqa.ptf.addressbook.folderSeven.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().goToHomeHeader();
    }

    @Test
    public void testsContactAddress() {
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData()
                            .withFirstName("Nik")
                            .withLastName("Petrov")
                            .withHomePhoneNumber("111")
                            .withWorkPhoneNumber("3333")
                            .withAddress("г. Спб, ул. Боровая, д.1, кв. 1")
                            .withEmail("erad@inbox.ru")
                            .withEmail3("erad3@inbox.ru"),
                    true);
            app.goTo().goToHomeHeader();
        }
        ContactData contact = app.contact().almostAll().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        String expectedResult = contact.getAddress().replaceAll("\\s", "");
        String actualResult = cleaned(contactInfoFromEditForm.getAddress());
        System.out.println(expectedResult);
        System.out.println(actualResult);

        assertThat(expectedResult, equalTo(actualResult));
    }

    public static String cleaned(String address) {

        return address.replaceAll("\\s", "");
    }
}