package ru.stqa.pft.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.appmanager.ApplicationManager;

public class TestBase {

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
}