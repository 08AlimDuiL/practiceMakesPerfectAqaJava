package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

    @Test
    public void testGroupDeletion() throws Exception {
        app.getNavigationHelper().goToGroupPageHeader();

        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }

//        int before = app.getGroupHelper().getGroupCount();
//        System.out.println("Groups before: " + before);
        List<GroupData> before = app.getGroupHelper().getGroupList();

        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();

        app.getGroupHelper().returnToGroupPage();

//        int after = app.getGroupHelper().getGroupCount();
//        System.out.println("Groups after: " + after);
        List<GroupData> after = app.getGroupHelper().getGroupList();

        Assert.assertEquals(after.size(), before.size() - 1);
    }
}
