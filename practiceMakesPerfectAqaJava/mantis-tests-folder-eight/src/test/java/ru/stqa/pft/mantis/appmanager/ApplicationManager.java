package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    public WebDriver wd;

    private String browser;
    private RegestrationHelper regestrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;// folder 8.6
    private JamesHelperOldWithTelnet jamesHelper;

    private NavigationHelper navigationHelper;
    private LoginHelper loginHelper;
    private UserManagementHelper userManagementHelper;
    //  private UserEditHelper userEditHelper;

    public ApplicationManager(String browser) {

        properties = new Properties();
        this.browser = browser;
    }

    public void init() throws IOException {

        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if (wd != null) {

            wd.quit();
        }
    }

    public HttpSession newSession() {

        return new HttpSession(this);
    }

    public String getProperty(String key) {

        return properties.getProperty(key);
    }

    public RegestrationHelper registration() {
        if (regestrationHelper == null) {
            regestrationHelper = new RegestrationHelper(this);// ленивая инициализация
        }

        return regestrationHelper;
    }

    public FtpHelper ftp() {
        if (ftp == null) {
            ftp = new FtpHelper(this);// ленивая инициализация
        }

        return ftp;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals("firefox")) {
                wd = new FirefoxDriver();
            } else if (browser.equals("chrome")) {
                wd = new ChromeDriver();
            }
            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }

        return wd;
    }

    public MailHelper mail() { // folder 8.6

        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }

        return mailHelper;
    }

    public JamesHelperOldWithTelnet james() {

        if (jamesHelper == null) {
            jamesHelper = new JamesHelperOldWithTelnet(this);
        }

        return jamesHelper;
    }

    public NavigationHelper navigation() {
        if (navigationHelper == null) {
            navigationHelper = new NavigationHelper(this);
        }
        return navigationHelper;
    }

    public LoginHelper login() {
        if (loginHelper == null) {
            loginHelper = new LoginHelper(this);
        }
        return loginHelper;
    }

    public UserManagementHelper userManagement() {

        if (userManagementHelper == null) {
            userManagementHelper = new UserManagementHelper(this);
        }

        return userManagementHelper;
    }

    public UserEditHelper userEdit() {

        return new UserEditHelper(this); // создаем новый экземпляр каждый раз
    }

}