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

    private By userTableRows = By.cssSelector("table tbody tr");
    private By userManagementLink = By.linkText("Управление пользователями");
    private By usernameLinks = By.cssSelector("td a[href*='manage_user_edit_page.php']");

    public UserManagementHelper goToUserManagement() {
        wd.findElement(userManagementLink).click();

        return this;
    }

    public UserEditHelper selectRandomUserWithOutAdmin() {

        List<WebElement> rows = wd.findElements(userTableRows);

        List<WebElement> nonAdminRows = rows.stream()
                .filter(row -> {
                    WebElement userLink = row.findElement(usernameLinks);
                    return !userLink.getText().equals("administrator");
                })
                .collect(Collectors.toList());

        if (nonAdminRows.isEmpty()) {
            throw new RuntimeException("Не найдено пользователей кроме администратора");
        }

        WebElement randomRow = nonAdminRows.get(random.nextInt(nonAdminRows.size()));

        List<WebElement> cells = randomRow.findElements(By.tagName("td"));
        String username = cells.get(0).findElement(usernameLinks).getText();
        String email = cells.get(2).getText();


        selectedUser = new UserData()
                .withUsername(username)
                .withEmail(email);

        cells.get(0).findElement(usernameLinks).click();

        System.out.println("Выбран пользователь: " + username + " с email: " + email);

        return app.userEdit();
    }

    public UserData selectRandomUserWithOutAdminAndGetData() {
        List<WebElement> rows = wd.findElements(userTableRows);

        List<WebElement> nonAdminRows = rows.stream()
                .filter(row -> {
                    WebElement userLink = row.findElement(usernameLinks);
                    return !userLink.getText().equals("administrator");
                })
                .collect(Collectors.toList());

        if (nonAdminRows.isEmpty()) {
            throw new RuntimeException("Не найдено пользователей кроме администратора");
        }

        WebElement randomRow = nonAdminRows.get(random.nextInt(nonAdminRows.size()));

        List<WebElement> cells = randomRow.findElements(By.tagName("td"));
        String username = cells.get(0).findElement(usernameLinks).getText();
        String email = cells.get(2).getText();

        // Создаем и возвращаем UserData
        UserData selectedUser = new UserData()
                .withUsername(username)
                .withEmail(email);

        // Кликаем по пользователю
        cells.get(0).findElement(usernameLinks).click();

        System.out.println("Выбран пользователь: " + username + " с email: " + email);

        return selectedUser;
    }


    public UserData getSelectedUser() {
        if (selectedUser == null) {
            throw new RuntimeException("Пользователь еще не выбран");
        }
        return selectedUser;
    }
}