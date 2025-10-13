package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ApplicationManager {
    public WebDriver wd;
    private String browser;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private ContactHelper contactHelper;
    private GroupHelper groupHelper;

    public WebDriverWait wait;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        System.out.println("=== Запуск браузера: " + browser + " ===");

        if (browser.equals("firefox")) {
            wd = new FirefoxDriver();
        } else if (browser.equals("chrome")) {
            wd = new ChromeDriver();
        }
        // wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wait = new WebDriverWait(wd, Duration.ofSeconds(10));
        wd.get("http://127.0.0.1/addressbook/");
        navigationHelper = new NavigationHelper(wd);
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
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

    public ContactHelper getContactHelper() {

        return contactHelper;
    }
}
