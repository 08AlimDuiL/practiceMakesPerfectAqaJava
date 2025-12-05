package ru.stqa.pft.addressbook.tests.dbHW15And16.contacts;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class HbContactCreationTest extends TestBase {

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
            }.getType());

            return contacts.stream().map((g) -> new Object[]{g})
                    .collect(Collectors.toList())
                    .iterator();
        }
    }

    @Test(dataProvider = "validContactsFromJson", description = "Task 15.4")
    public void testHbContactCreation(ContactData contactFromJson) {

        Contacts before = app.db().contacts();

        System.out.println("==================================================================");
        for (ContactData contact : before) {
            System.out.println(contact);
            System.out.println(contact.getGroups());
        }

        System.out.println("Total contacts before: " + before.size());
        app.goTo().goToHomeHeader();
        app.contact().create(contactFromJson, true);
        System.out.println("Created contact: " + contactFromJson.getId());

        Contacts after = app.db().contacts();


        System.out.println("==================================================================");
        for (ContactData contact : after) {
            System.out.println(contact);
        }
        System.out.println("Total groups after: " + after.size());

        assertThat(after.size(), equalTo(before.size() + 1));

        int maxId = after
                .stream()
                .mapToInt(ContactData::getId)
                .max()
                .orElse(0);
        Contacts expected = new Contacts(before);
        expected.add(contactFromJson.withId(maxId));

        assertThat(after, equalTo(expected));
    }
}