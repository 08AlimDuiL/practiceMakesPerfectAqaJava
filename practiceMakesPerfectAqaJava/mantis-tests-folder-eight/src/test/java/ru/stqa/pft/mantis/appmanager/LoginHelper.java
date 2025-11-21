package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginHelper {

    private WebDriver wd;
    private ApplicationManager app;

    public LoginHelper(ApplicationManager app) {
        this.app = app;
        this.wd = app.getDriver();
    }

    public void loginAsAdmin() {

        login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    }

    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        wd.findElement(By.name("username")).sendKeys(username);
        wd.findElement(By.name("password")).sendKeys(password);
        wd.findElement(By.cssSelector("input[type='submit']")).click();
    }
}