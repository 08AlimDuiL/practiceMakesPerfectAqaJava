package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPageHeader();

        int before = app.getGroupHelper().getGroupCount();
        System.out.println("Groups before: " + before);

        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        //  app.logout();

        int after = app.getGroupHelper().getGroupCount();
        System.out.println("Groups after: " + after);

        Assert.assertEquals(after, before + 1);
    }
}