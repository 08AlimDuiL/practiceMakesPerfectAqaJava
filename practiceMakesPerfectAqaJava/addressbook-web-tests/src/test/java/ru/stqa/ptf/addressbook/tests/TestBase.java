package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.ptf.addressbook.appmanager.ApplicationManager;

public class TestBase {

    //protected static final ApplicationManager app = new ApplicationManager("firefox");

    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", "chrome"));

    @BeforeSuite
    public void setUp() throws Exception {

        app.init();
    }

    @AfterSuite
    public void tearDown() {

        app.stop();
    }
}
