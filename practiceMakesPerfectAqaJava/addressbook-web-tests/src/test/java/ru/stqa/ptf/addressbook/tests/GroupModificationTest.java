package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPageHeader();
        //    if (!app.group().isThereAGroup()) {
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test9"));
        }
    }

    @Test
    public void testGroupModificationFirst() {
        int before = app.group().getGroupCount();
        System.out.println("Groups before === " + before);
        app.group().selectGroup();
        app.group().initGroupModification();
        app.group().fillGroupForm(new GroupData().withName("test1").withHeader("test5").withFooter("test5"));
        app.group().submitGroupModification();
        app.group().returnToGroupPage();
        int after = app.group().getGroupCount();
        System.out.println("Groups after === " + after);

        Assert.assertEquals(after, before);
    }

    @Test
    public void testGroupModification() {
        List<GroupData> before = app.group().list();
        app.group().selectGroup();
        app.group().initGroupModification();
        app.group().fillGroupForm(new GroupData().withName("test1").withHeader("test5").withFooter("test5"));
        app.group().submitGroupModification();
        app.group().returnToGroupPage();
        List<GroupData> after = app.group().list();

        Assert.assertEquals(after.size(), before.size());
    }

    @Test
    public void testGroupModificationList() {
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        app.group().selectGroupByIndex(index);
        app.group().initGroupModification();
        GroupData groupData = new GroupData()
                .withId(before.get(index).getId())
                .withName("test1")
                .withHeader("test5")
                .withFooter("test5");
        app.group().fillGroupForm(groupData);
        app.group().submitGroupModification();
        app.group().returnToGroupPage();
        List<GroupData> after = app.group().list();

        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(groupData);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

    @Test
    public void testGroupModificationListOrder() {
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        GroupData groupData = new GroupData()
                .withId(before.get(index).getId())
                .withName("test1")
                .withHeader("test5")
                .withFooter("test5");
        app.group().modify(index, groupData);
        List<GroupData> after = app.group().list();

        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(groupData);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

    @Test
    public void testGroupModificationSet() {
        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData groupData = new GroupData()
                .withId(modifiedGroup.getId())
                .withName("test1")
                .withHeader("test5")
                .withFooter("test5");
        app.group().modify(groupData);
        Set<GroupData> after = app.group().all();

        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedGroup);
        before.add(groupData);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}