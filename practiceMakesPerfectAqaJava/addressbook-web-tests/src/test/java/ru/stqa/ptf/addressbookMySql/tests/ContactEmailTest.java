package ru.stqa.ptf.addressbookMySql.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbookMySql.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().goToHomeHeader();
    }

    @Test
    public void testsContactEmail() {
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

        assertThat(contact.getAllEmails(), equalTo(mergeAllEmails(contactInfoFromEditForm)));
    }

    private String mergeAllEmails(ContactData contact) {

        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactEmailTest::cleaned)  // метод обратных проверок
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String email) {

        return email.replaceAll("\\s", "");
    }
}
