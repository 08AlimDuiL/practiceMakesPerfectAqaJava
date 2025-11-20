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

    private By resetPasswordButton = By.xpath("//form[@id='manage-user-reset-form']//input[@value='Сбросить пароль']");
    private By continueButton = By.xpath("//input[@value='Продолжить']");
    private By successMessage = By.xpath("//div[@id='content']/div");

    public UserEditHelper clickResetPassword() throws InterruptedException {
        wd.findElement(resetPasswordButton).click();

        wait.until(ExpectedConditions.urlContains("manage_user_reset.php"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));

        System.out.println("Перешли на страницу: " + wd.getCurrentUrl());
        System.out.println("Сообщение: " + wd.findElement(successMessage).getText());

        return this;
    }

    public UserEditHelper clickContinue() {
        wd.findElement(continueButton).click();

        wait.until(ExpectedConditions.urlContains("manage_user_page.php"));

        return this;
    }
}