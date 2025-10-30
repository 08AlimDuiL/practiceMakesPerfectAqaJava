package ru.stqa.ptf.addressbook.folderSeven.tests.groups;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
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

public class GroupCreationTests extends TestBase {

    Logger logger = LoggerFactory.getLogger(GroupCreationTests.class);

    @DataProvider //  description = "Folder 6.7&8"
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
            }.getType()); // List<GroupData>.class

            return groups.stream().map((g) -> new Object[]{g})
                    .collect(Collectors.toList())
                    .iterator();
        }
//        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));
//        String json = "";
//        String line = reader.readLine();
//        while (line != null) {
//            json += line;
//            line = reader.readLine();
//        }
//        Gson gson = new Gson();
//        List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
//        }.getType()); // List<GroupData>.class
//
//        return groups.stream().map((g) -> new Object[]{g})
//                .collect(Collectors.toList())
//                .iterator();
    }

    @DataProvider //  description = "Folder 6.6"
    public Iterator<Object[]> validGroups66() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.allowTypes(new Class[]{GroupData.class});  // !!!!
        xstream.processAnnotations(GroupData.class);
        List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);

        return groups.stream().map((g) -> new Object[]{g})
                .collect(Collectors.toList())
                .iterator();
    }

    @DataProvider //  description = "Folder 6.5"
    public Iterator<Object[]> validGroups65() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[]{
                            new GroupData()
                                    .withName(split[0])
                                    .withHeader(split[1])
                                    .withFooter(split[2])
                    }
            );
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider //  description = "Folder 6.4"
    public Iterator<Object[]> validGroups() {
        List<Object[]> list = new ArrayList<Object[]>();
//        list.add(new Object[]{"test1' ", "header 1", "footer 1"});
//        list.add(new Object[]{"test2", "header 2", "footer 2"});
//        list.add(new Object[]{"test3", "header 3", "footer 3"});
        list.add(new Object[]{new GroupData().withName("test1").withHeader("header1").withFooter("footer 1")});
        list.add(new Object[]{new GroupData().withName("test2").withHeader("header2").withFooter("footer3")});
        list.add(new Object[]{new GroupData().withName("test3").withHeader("header2").withFooter("footer3")});

        return list.iterator();
    }

//----------------------------------------------------------------------------------------------------------------------

    @Test(dataProvider = "validGroupsFromJson", description = "Folder 6.7")
    public void testGroupCreationParam67(GroupData group) {
        //logger.info("Start test testGroupCreationParam67");
        app.goTo().groupPageHeader();
        Groups before = app.group().all();
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(
                before.withAdded(
                        group
                                .withId(after
                                        .stream().mapToInt(g -> g.getId()).max().getAsInt()))
        ));
        //logger.info("Stop test testGroupCreationParam67");
    }

    @Test(dataProvider = "validGroups66", description = "Folder 6.6")
    public void testGroupCreationParam66(GroupData group) {
        app.goTo().groupPageHeader();
        Groups before = app.group().all();
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(
                before.withAdded(
                        group
                                .withId(after
                                        .stream().mapToInt(g -> g.getId()).max().getAsInt()))
        ));
    }

    @Test(dataProvider = "validGroups65", description = "Folder 6.5")
    public void testGroupCreationParam65(GroupData group) {
        app.goTo().groupPageHeader();
        Groups before = app.group().all();
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(
                before.withAdded(
                        group
                                .withId(after
                                        .stream().mapToInt(g -> g.getId()).max().getAsInt()))
        ));
    }

    @Test(dataProvider = "validGroups", description = "Folder 6.4")
    public void testGroupCreationParam(GroupData group) { ////String name, String header, String footer
        //String[] names = new String[]{"test1", "test2", "test3"};
        //  for (String name : names) {
        ////  GroupData group = new GroupData().withName(name).withHeader(header).withFooter(footer);
        app.goTo().groupPageHeader();
        Groups before = app.group().all();
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(
                before.withAdded(
                        group
                                .withId(after
                                        .stream().mapToInt(g -> g.getId()).max().getAsInt()))
        ));
        //}
    }

    @Test
    public void testGroupCreationFirst() {
        app.goTo().groupPageHeader();
        int before = app.group().getGroupCount();
        System.out.println("Groups before === " + before);
        app.group().create(new GroupData().withName("test9"));
        int after = app.group().getGroupCount();
        System.out.println("Groups after === " + after);

        Assert.assertEquals(after, before + 1);
    }

    @Test
    public void testGroupCreation() {
        app.goTo().groupPageHeader();
        List<GroupData> before = app.group().list();
        app.group().create(new GroupData().withName("test9"));
        List<GroupData> after = app.group().list();

        Assert.assertEquals(after.size(), before.size() + 1);
    }

    @Test
    public void testGroupCreationList() {
        app.goTo().groupPageHeader();
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData().withName("test9");
        app.group().create(group);
        List<GroupData> after = app.group().list();

        Assert.assertEquals(after.size(), before.size() + 1);
  /*
        int max = 0;
        for (GroupData g : after) {
            if (g.getId() > max) {
                max = g.getId();
            }
        }
        Comparator<? super GroupData> byId = new Comparator<GroupData>() {
            @Override
            public int compare(GroupData o1, GroupData o2) {

                return Integer.compare(o1.getId(), o2.getId());
            }
        };
*/
        Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        int max1 = after.stream().max(byId).get().getId();
        //int max1 = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
        group.withId(max1);
        before.add(group);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

    @Test
    @Ignore
    public void testGroupCreationListOrder() {
        app.goTo().groupPageHeader();
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData().withName("test9");
        app.group().create(group);
        List<GroupData> after = app.group().list();

        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals((before), (after));
        //  return Objects.equals(name, groupData.name);!!
    }

    @Test
    public void testGroupCreationSet() {
        app.goTo().groupPageHeader();
        Set<GroupData> before = app.group().allAsSet();
        GroupData group = new GroupData().withName("test10");
        app.group().create(group);
        Set<GroupData> after = app.group().allAsSet();

        Assert.assertEquals(after.size(), before.size() + 1);

        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);

        Assert.assertEquals((before), (after));
    }

    @Test
    public void testGroupCreationSetHamcrest() {
        app.goTo().groupPageHeader();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test11");
        app.group().create(group);
        Groups after = app.group().all();

        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        MatcherAssert.assertThat(after.size(), CoreMatchers.equalTo(before.size() + 1));
    }

    @Test
    public void testBadGroupCreationSetHamcrest() {
        app.goTo().groupPageHeader();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test'");
        app.group().create(group);

        assertThat(app.group().count(), equalTo(before.size()));

        Groups after = app.group().all();

        assertThat(after, equalTo(before));
    }
}