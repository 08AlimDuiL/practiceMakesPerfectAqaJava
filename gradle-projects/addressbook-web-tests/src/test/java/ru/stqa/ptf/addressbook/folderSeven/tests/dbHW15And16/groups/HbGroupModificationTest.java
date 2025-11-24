package ru.stqa.ptf.addressbook.folderSeven.tests.dbHW15And16.groups;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.folderSeven.model.GroupData;
import ru.stqa.ptf.addressbook.folderSeven.model.Groups;
import ru.stqa.ptf.addressbook.folderSeven.tests.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HbGroupModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPageHeader();
            app.group().create(new GroupData().withName("test150"));
        }
    }

    @Test(description = "task 15.2 ")
    public void testHbGroupModification() {

        Groups before = app.db().groups();

        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId())
                .withName("test152")
                .withHeader("test152")
                .withFooter("test152");
        app.goTo().groupPageHeader();
        app.group().modify(group);

        assertThat(app.group().count(), equalTo(before.size()));

        Groups after = app.db().groups();

        assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));

        verifyGroupListInUi();
    }
}