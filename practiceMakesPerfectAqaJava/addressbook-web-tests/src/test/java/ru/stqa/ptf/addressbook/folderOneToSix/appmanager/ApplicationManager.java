package ru.stqa.ptf.addressbook.folderOneToSix.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
    private final Properties properties;
    public WebDriver wd;
    private String browser;





    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private ContactHelper contactHelper;
    private GroupHelper groupHelper;

    public WebDriverWait wait;

    public ApplicationManager(String browser) {
        properties = new Properties();
        this.browser = browser;
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        System.out.println("=== Запуск браузера: " + browser + " ===");

        if (browser.equals("firefox")) {
            wd = new FirefoxDriver();
        } else if (browser.equals("chrome")) {
            wd = new ChromeDriver();
        }
        // wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wait = new WebDriverWait(wd, Duration.ofSeconds(10));
        // wd.get("http://127.0.0.1/addressbook/");
        wd.get(properties.getProperty("web.baseUrl"));
        navigationHelper = new NavigationHelper(wd);
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper = new SessionHelper(wd);
        //sessionHelper.login("admin", "secret");
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public void goToHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }

    public void logout() {

        wd.findElement(By.linkText("Logout")).click();
    }

    public void stop() {

        wd.quit();
    }

    public GroupHelper group() {

        return groupHelper;
    }

    public NavigationHelper goTo() {

        return navigationHelper;
    }

    public ContactHelper contact() {

        return contactHelper;
    }
}
