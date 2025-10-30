package ru.stqa.ptf.addressbook.folderSeven.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.ptf.addressbook.folderSeven.appmanager.ApplicationManager;
import ru.stqa.ptf.addressbook.folderSeven.model.GroupData;
import ru.stqa.ptf.addressbook.folderSeven.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", "chrome"));

    @BeforeSuite
    public void setUp() throws Exception {

        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {

        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p) {

        logger.info("Start test " + m.getName() + "with parametrs " + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {

        logger.info("Stop test " + m.getName());
    }

    //Folder 7.5
    public void verifyGroupListInUi() {
        if (Boolean.getBoolean("verifyUi")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups
                    , equalTo(dbGroups.
                            stream().map((g) -> new GroupData()
                                    .withId(g.getId())
                                    .withName(g.getName()))
                            .collect(Collectors.toSet()))
            );
        }
    }
}
