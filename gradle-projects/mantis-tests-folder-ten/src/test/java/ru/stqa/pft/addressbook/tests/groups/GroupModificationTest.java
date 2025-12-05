package ru.stqa.pft.addressbook.tests.groups;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPageHeader();
            app.group().create(new GroupData().withName("test9"));
        }
    }

    //Folder 7.4
    @Test
    public void testGroupModification() {
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId())
                .withName("test1")
                .withHeader("test5")
                .withFooter("test5");
        app.goTo().groupPageHeader();
        app.group().modify(group);

        assertThat(app.group().count(), equalTo(before.size()));

        Groups after = app.db().groups();

        assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));

        verifyGroupListInUi();     //Folder 7.5
    }
}