package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

    @Test
    public void testGroupDeletionFirst() {
        app.getNavigationHelper().goToGroupPageHeader();

        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }

        int before = app.getGroupHelper().getGroupCount();
        System.out.println("Groups before: " + before);

        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();

        app.getGroupHelper().returnToGroupPage();

        int after = app.getGroupHelper().getGroupCount();
        System.out.println("Groups after: " + after);

        Assert.assertEquals(after, before - 1);
    }

    @Test
    public void testGroupDeletionList() {
        app.getNavigationHelper().goToGroupPageHeader();

        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }

        List<GroupData> before = app.getGroupHelper().getGroupList();

        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();

        app.getGroupHelper().returnToGroupPage();

        List<GroupData> after = app.getGroupHelper().getGroupList();

        Assert.assertEquals(after.size(), before.size() - 1);
    }

    @Test
    public void testGroupDeletion1() {
        app.getNavigationHelper().goToGroupPageHeader();

        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }

        List<GroupData> before = app.getGroupHelper().getGroupList();

        app.getGroupHelper().selectGroupByIndex(before.size() - 1);
        app.getGroupHelper().deleteSelectedGroup();

        app.getGroupHelper().returnToGroupPage();

        List<GroupData> after = app.getGroupHelper().getGroupList();

        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
//        for (int i = 0; i < after.size(); i++) {
//
//            Assert.assertEquals(before.get(i), after.get(i));
//        }
        Assert.assertEquals(before, after);
    }
}
