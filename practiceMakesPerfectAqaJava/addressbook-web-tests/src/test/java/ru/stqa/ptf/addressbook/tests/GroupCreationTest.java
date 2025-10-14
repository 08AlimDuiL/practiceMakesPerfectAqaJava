package ru.stqa.ptf.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupCreationTest extends TestBase {

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