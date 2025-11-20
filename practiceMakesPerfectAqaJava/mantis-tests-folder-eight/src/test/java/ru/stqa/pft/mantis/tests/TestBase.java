package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;

public class TestBase {

    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", "chrome"));

    @BeforeSuite
    public void setUp() throws Exception {

        app.init();
//
//        File configFile = new File("src/test/resources/config_inc.php");
//
//        System.out.println("Working directory=============>>>>>>>>>>>>>>>> " + System.getProperty("user.dir"));
//        System.out.println("Config file path=============>>>>>>>>>>>>>>>> " + configFile.getAbsolutePath());
//        System.out.println("File exists=============>>>>>>>>>>>>>>>> " + configFile.exists());
//
//        if (configFile.exists()) {
//            // app.ftp().upload(configFile, "config_inc.php", "config_inc.php.bak");
//            app.ftp().upload(configFile, "mantisbt-1.3.20/config/config_inc.php", "mantisbt-1.3.20/config/config_inc.php.bak");
//            Thread.sleep(3000);
//        } else {
//            System.out.println("WARNING: Config file not found at: " + configFile.getAbsolutePath());
//        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
//        app.ftp().restore("mantisbt-1.3.20/config/config_inc.php.bak", "mantisbt-1.3.20/config/config_inc.php");
//        //app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }
}