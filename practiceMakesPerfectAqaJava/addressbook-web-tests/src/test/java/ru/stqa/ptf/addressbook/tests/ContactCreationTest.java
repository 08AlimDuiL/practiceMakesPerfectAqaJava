package ru.stqa.ptf.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType()); // List<ContactData>.class

            return contacts.stream().map((g) -> new Object[]{g})
                    .collect(Collectors.toList())
                    .iterator();
        }
    }

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().goToHomeHeader();
    }

    @Test(dataProvider = "validContactsFromJson", description = "Task 13")
    public void testCreationContactJson(ContactData contact) {
        Set<ContactData> before = app.contact().all();
        app.contact().create(contact, true);
        app.goToHomePage();
        Set<ContactData> after = app.contact().all();

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
    @Ignore
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
                .mapToInt(ContactData::getId)  // .mapToInt(contact -> contact.getId()) Лямбда - это короткий синтаксис для записи анонимных функций.
                .max()
                .orElse(0);
        Set<ContactData> expected = new HashSet<>(before);
        expected.add(contact.withId(maxId));

        assertThat(after, equalTo(expected));
    }

    @Test(description = "Folder 6.1")
    public void testContactCreation() {
        app.goTo().goToHomeHeader();
        app.contact().initContactCreation();

        File photo = new File("src/test/resources/stru.png");
        System.out.println(photo.getAbsolutePath());
        app.contact().fillFormContactWithFoto(
                new ContactData()
                        .withFirstName("Petya")
                        .withLastName("Petrov")
                        .withPhoto(photo),
                true
        );
        app.contact().enterContact();
        app.goTo().goToHomeHeader();

    }

    @Test(description = "Folder 6.1 Helper test")
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/stru.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }
}
/*
.mapToInt(new ToIntFunction<ContactData>() {
    @Override
    public int applyAsInt(ContactData contact) {
        return contact.getId();
})
 */