package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreationFirst() {
        app.getNavigationHelper().goToGroupPageHeader();

        int before = app.getGroupHelper().getGroupCount();
        System.out.println("Groups before === " + before);

        app.getGroupHelper().createGroup(new GroupData("test3", null, null));

        int after = app.getGroupHelper().getGroupCount();
        System.out.println("Groups after === " + after);

        Assert.assertEquals(after, before + 1);
    }

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPageHeader();

        List<GroupData> before = app.getGroupHelper().getGroupList();

        app.getGroupHelper().createGroup(new GroupData("test3", null, null));

        List<GroupData> after = app.getGroupHelper().getGroupList();

        Assert.assertEquals(after.size(), before.size() + 1);
    }

    @Test
    public void testGroupCreationList() {

        app.getNavigationHelper().goToGroupPageHeader();

        List<GroupData> before = app.getGroupHelper().getGroupList();

        GroupData group = new GroupData("test2", null, null);
        app.getGroupHelper().createGroup(group);

        List<GroupData> after = app.getGroupHelper().getGroupList();

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

        Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        int max1 = after.stream().max(byId).get().getId();

   */
        int max1 = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
        group.setId(max1);
        before.add(group);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

    @Test
    public void testGroupCreationListOrder() {

        app.getNavigationHelper().goToGroupPageHeader();

        List<GroupData> before = app.getGroupHelper().getGroupList();

        GroupData group = new GroupData("test2", null, null);
        app.getGroupHelper().createGroup(group);

        List<GroupData> after = app.getGroupHelper().getGroupList();

        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals((before), (after));
    }
}