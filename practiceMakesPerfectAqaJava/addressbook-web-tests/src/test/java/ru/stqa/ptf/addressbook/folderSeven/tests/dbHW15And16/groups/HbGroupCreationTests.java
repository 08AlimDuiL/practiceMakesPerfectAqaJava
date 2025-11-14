package ru.stqa.ptf.addressbook.folderSeven.tests.dbHW15And16.groups;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.folderSeven.model.GroupData;
import ru.stqa.ptf.addressbook.folderSeven.model.Groups;
import ru.stqa.ptf.addressbook.folderSeven.tests.TestBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HbGroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType());

            return groups.stream().map((g) -> new Object[]{g})
                    .collect(Collectors.toList())
                    .iterator();
        }
    }

    @Test(dataProvider = "validGroupsFromJson", description = "task 15.1 ")
    public void testHbGroupCreation(GroupData groupFromJson) {

        Groups before = app.db().groups();

        System.out.println("==================================================================");
        for (GroupData group : before) {
            System.out.println(group);
        }
        System.out.println("Total groups before: " + before.size());

        app.goTo().groupPageHeader();
        app.group().create(groupFromJson);
        System.out.println("Created group: " + groupFromJson.getName());

        Groups after = app.db().groups();

        System.out.println("==================================================================");
        for (GroupData group : after) {
            System.out.println(group);
        }
        System.out.println("Total groups after: " + after.size());

        assertThat(after.size(), equalTo(before.size() + 1));
    }
}