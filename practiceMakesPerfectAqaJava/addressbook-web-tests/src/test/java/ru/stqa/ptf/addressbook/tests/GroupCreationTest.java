package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPageHeader();

        List<GroupData> before = app.getGroupHelper().getGroupList();

        // int before = app.getGroupHelper().getGroupCount();
       // System.out.println("Groups before: " + before);

        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        //  app.logout();

        List<GroupData> after = app.getGroupHelper().getGroupList();
        //int after = app.getGroupHelper().getGroupCount();
       // System.out.println("Groups after: " + after);

        Assert.assertEquals(after.size(), before.size() + 1);
    }
}