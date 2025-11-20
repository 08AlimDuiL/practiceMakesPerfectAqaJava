package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {

    private WebDriver wd;
    private ApplicationManager app;

    public NavigationHelper(ApplicationManager app) {
        this.app = app;
        this.wd = app.getDriver();
    }

    private By userLink = By.className("account-menu-link");
    private By managementLink = By.className("manage-menu-link");
    private By logoutLink = By.id("logout-link");

    private By userManagementLink = By.cssSelector("a[href*='manage_user_page.php']");

    public void goToUserAccount() {

        wd.findElement(userLink).click();
    }

    public void logout() {

        wd.findElement(logoutLink).click();
    }

    public UserManagementHelper goToManagementPage() {

        wd.findElement(managementLink).click();

        return app.userManagement();
    }

    public void goToUserManagement() {

        wd.findElement(userManagementLink).click();
    }
}
