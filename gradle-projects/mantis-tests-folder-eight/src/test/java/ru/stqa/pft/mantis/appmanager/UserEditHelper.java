package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserEditHelper {

    private WebDriver wd;
    private ApplicationManager app;
    private WebDriverWait wait;

    public UserEditHelper(ApplicationManager app) {

        this.app = app;
        this.wd = app.getDriver();
        this.wait = new WebDriverWait(wd, java.time.Duration.ofSeconds(10));
    }

    private static final By RESET_PASSWORD_BUTTON = By.xpath("//form[@id='manage-user-reset-form']//input[@value='Сбросить пароль']");
    private static final By CONTINUE_BUTTON = By.xpath("//input[@value='Продолжить']");
    private static final By SUCCESS_MESSAGE = By.xpath("//div[@id='content']/div");

    public UserEditHelper clickResetPassword() throws InterruptedException {
        wd.findElement(RESET_PASSWORD_BUTTON).click();

        wait.until(ExpectedConditions.urlContains("manage_user_reset.php"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_MESSAGE));

        System.out.println("Перешли на страницу: " + wd.getCurrentUrl());
        System.out.println("Сообщение: " + wd.findElement(SUCCESS_MESSAGE).getText());

        return this;
    }

    public UserEditHelper clickContinue() {
        wd.findElement(CONTINUE_BUTTON).click();

        wait.until(ExpectedConditions.urlContains("manage_user_page.php"));

        return this;
    }
}