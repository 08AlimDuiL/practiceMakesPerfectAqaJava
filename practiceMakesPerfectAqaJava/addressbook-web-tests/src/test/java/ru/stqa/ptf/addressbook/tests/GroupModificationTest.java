package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class GroupModificationTest extends TestBase {

    @Test
    public void testGroupModificationFirst() {
        app.getNavigationHelper().goToGroupPageHeader();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }

        int before = app.getGroupHelper().getGroupCount();
        System.out.println("Groups before === " + before);

        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test5", "test5", "test5"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();

        int after = app.getGroupHelper().getGroupCount();
        System.out.println("Groups after === " + after);

        Assert.assertEquals(after, before);
    }

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToGroupPageHeader();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }

        List<GroupData> before = app.getGroupHelper().getGroupList();

        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test5", "test5", "test5"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();

        List<GroupData> after = app.getGroupHelper().getGroupList();

        Assert.assertEquals(after.size(), before.size());
    }

    @Test
    public void testGroupModificationList() {
        app.getNavigationHelper().goToGroupPageHeader();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }

        List<GroupData> before = app.getGroupHelper().getGroupList();

        app.getGroupHelper().selectGroupByIndex(before.size() - 1);
        app.getGroupHelper().initGroupModification();

        GroupData groupData = new GroupData(before.get(before.size() - 1).getId(), "test1", "test5", "test5");

        app.getGroupHelper().fillGroupForm(groupData);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();

        List<GroupData> after = app.getGroupHelper().getGroupList();

        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(groupData);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

    @Test
    public void testGroupModificationListOrder() {
        app.getNavigationHelper().goToGroupPageHeader();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }

        List<GroupData> before = app.getGroupHelper().getGroupList();

        app.getGroupHelper().selectGroupByIndex(before.size() - 1);
        app.getGroupHelper().initGroupModification();

        GroupData groupData = new GroupData(before.get(before.size() - 1).getId(), "test1", "test5", "test5");

        app.getGroupHelper().fillGroupForm(groupData);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();

        List<GroupData> after = app.getGroupHelper().getGroupList();

        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(groupData);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
