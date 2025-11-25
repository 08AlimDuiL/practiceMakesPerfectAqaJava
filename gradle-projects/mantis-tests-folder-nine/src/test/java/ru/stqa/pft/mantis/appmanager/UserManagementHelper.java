package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.mantis.model.UserData;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class UserManagementHelper {

    private WebDriver wd;
    private ApplicationManager app;
    private Random random = new Random();

    private UserData selectedUser;

    public UserManagementHelper(ApplicationManager app) {
        this.app = app;
        this.wd = app.getDriver();
    }

    private static final By USER_TABLE_ROWS = By.cssSelector("table tbody tr");
    private static final By USER_MANAGEMENT_LINK = By.linkText("Управление пользователями");
    private static final By USERNAME_LINKS = By.cssSelector("td a[href*='manage_user_edit_page.php']");

    private static final String ADMIN_USERNAME = "administrator";

    public UserManagementHelper goToUserManagement() {
        wd.findElement(USER_MANAGEMENT_LINK).click();

        return this;
    }

    public UserEditHelper selectRandomUserWithOutAdmin() {

        List<WebElement> rows = wd.findElements(USER_TABLE_ROWS);

        List<WebElement> nonAdminRows = rows.stream()
                .filter(row -> {
                    WebElement userLink = row.findElement(USERNAME_LINKS);
                    return !userLink.getText().equals(ADMIN_USERNAME);
                })
                .collect(Collectors.toList());

        if (nonAdminRows.isEmpty()) {
            throw new RuntimeException("Не найдено пользователей кроме администратора");
        }

        WebElement randomRow = nonAdminRows.get(random.nextInt(nonAdminRows.size()));

        List<WebElement> cells = randomRow.findElements(By.tagName("td"));
        String username = cells.get(0).findElement(USERNAME_LINKS).getText();
        String email = cells.get(2).getText();


        selectedUser = new UserData()
                .withUsername(username)
                .withEmail(email);

        cells.get(0).findElement(USERNAME_LINKS).click();

        System.out.println("Выбран пользователь: " + username + " с email: " + email);

        return app.userEdit();
    }

    public UserData getSelectedUser() {
        if (selectedUser == null) {
            throw new RuntimeException("Пользователь еще не выбран");
        }
        return selectedUser;
    }

    public int getUserCount() {

        List<WebElement> rows = wd.findElements(USER_TABLE_ROWS);
        return rows.size();
    }
}