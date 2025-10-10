package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPageHeader();

        List<GroupData> before = app.getGroupHelper().getGroupList();

        // int before = app.getGroupHelper().getGroupCount();
        // System.out.println("Groups before: " + before);

        app.getGroupHelper().createGroup(new GroupData("test3", null, null));
        //  app.logout();

        List<GroupData> after = app.getGroupHelper().getGroupList();
        //int after = app.getGroupHelper().getGroupCount();
        // System.out.println("Groups after: " + after);

        Assert.assertEquals(after.size(), before.size() + 1);
    }

    @Test
    public void testGroupCreationList() {
        app.getNavigationHelper().goToGroupPageHeader();

        List<GroupData> before = app.getGroupHelper().getGroupList();

        GroupData group = new GroupData("test3", null, null);
        app.getGroupHelper().createGroup(group);

        List<GroupData> after = app.getGroupHelper().getGroupList();

        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(group);
        int max = 0;
        for (GroupData g : after) {
            if(g.getId() > max) {
                max = g.getId();
            }
        }
        group.setId(max);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}