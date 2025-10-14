package ru.stqa.ptf.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPageHeader();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("test9"));
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

        assertEquals(after, before - 1);
    }

    @Test
    public void testGroupDeletionList() {
        List<GroupData> before = app.group().list();
        app.group().selectGroup();
        app.group().deleteSelectedGroup();
        app.group().returnToGroupPage();
        List<GroupData> after = app.group().list();

        assertEquals(after.size(), before.size() - 1);
    }

    @Test
    public void testGroupDeletionOrder() {
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        app.group().delete(index);
        List<GroupData> after = app.group().list();

        assertEquals(after.size(), before.size() - 1);

        before.remove(index);
//        for (int i = 0; i < after.size(); i++) {
//
//            Assert.assertEquals(before.get(i), after.get(i));
//        }

        assertEquals(before, after);
    }

    @Test
    public void testGroupDeletionSet() {
        Set<GroupData> before = app.group().allAsSet();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Set<GroupData> after = app.group().allAsSet();

        assertEquals(after.size(), before.size() - 1);

        before.remove(deletedGroup);

        assertEquals(before, after);
    }

    @Test
    public void testGroupDeletionSetHamcrest() {
        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Groups after = app.group().all();

        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.withoutAdded(deletedGroup)));
    }

    @Test
    public void testHashGroupDeletionSetHamcrest() {
        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);

        assertThat(app.group().count(), equalTo(before.size() - 1));

        Groups after = app.group().all();

        assertThat(after, equalTo(before.withoutAdded(deletedGroup)));
    }
}
