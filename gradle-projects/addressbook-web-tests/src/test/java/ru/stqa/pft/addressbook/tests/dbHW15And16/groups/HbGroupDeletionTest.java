package ru.stqa.pft.addressbook.tests.dbHW15And16.groups;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HbGroupDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPageHeader();
            app.group().create(new GroupData().withName("test150"));
        }
    }

    @Test(description = "task 15.3 ")
    public void testHbGroupDeletion() {

        Groups before = app.db().groups();

        System.out.println("==================================================================");
        for (GroupData group : before) {
            System.out.println(group);
        }
        System.out.println("Total groups before: " + before.size());

        GroupData deletedGroup = before.iterator().next();
        app.goTo().groupPageHeader();
        app.group().delete(deletedGroup);


        assertThat(app.group().count(), equalTo(before.size() - 1));

        Groups after = app.db().groups();

        System.out.println("==================================================================");
        for (GroupData group : after) {
            System.out.println(group);
        }
        System.out.println("Total groups after: " + after.size());

        assertThat(after, equalTo(before.withOut(deletedGroup)));

        verifyGroupListInUi();
    }
}