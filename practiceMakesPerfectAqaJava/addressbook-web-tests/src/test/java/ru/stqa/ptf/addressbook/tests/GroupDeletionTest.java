package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPageHeader();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("testNew", null, null));
        }
    }

    @Test
    public void testGroupDeletionFirst() {
        int before = app.group().getGroupCount();
        System.out.println("Groups before: " + before);

        app.group().selectGroup();
        app.group().deleteSelectedGroup();
        app.group().returnToGroupPage();

        int after = app.group().getGroupCount();
        System.out.println("Groups after: " + after);

        Assert.assertEquals(after, before - 1);
    }

    @Test
    public void testGroupDeletionList() {
        List<GroupData> before = app.group().list();

        app.group().selectGroup();
        app.group().deleteSelectedGroup();
        app.group().returnToGroupPage();

        List<GroupData> after = app.group().list();

        Assert.assertEquals(after.size(), before.size() - 1);
    }

    @Test
    public void testGroupDeletion1() {
        List<GroupData> before = app.group().list();

        int index = before.size() - 1;
        app.group().delete(index);

        List<GroupData> after = app.group().list();

        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
//        for (int i = 0; i < after.size(); i++) {
//
//            Assert.assertEquals(before.get(i), after.get(i));
//        }
        Assert.assertEquals(before, after);
    }
}
