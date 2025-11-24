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

    private static final By USER_LINK = By.className("account-menu-link");
    private static final By MANAGEMENT_LINK = By.className("manage-menu-link");
    private static final By LOGOUT_LINK = By.id("logout-link");
    private static final By USER_MANAGEMENT_LINK = By.cssSelector("a[href*='manage_user_page.php']");

    public void goToUserAccount() {

        wd.findElement(USER_LINK).click();
    }

    public void logout() {

        wd.findElement(LOGOUT_LINK).click();
    }

    public UserManagementHelper goToManagementPage() {

        wd.findElement(MANAGEMENT_LINK).click();

        return app.userManagement();
    }

    public void goToUserManagement() {

        wd.findElement(USER_MANAGEMENT_LINK).click();
    }
}
