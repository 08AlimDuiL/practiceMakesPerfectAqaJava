package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {

        super(wd);
    }

    public NavigationHelper groupPageHeader() {
        click(By.cssSelector(".admin"));
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("new")));

        return this;
    }

    public NavigationHelper groupsDashboard() {

        if (isElementPresent(
                By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return this;
        }
        click(By.linkText("groups"));
        return this;
    }


    public void goToHomeHeader() {
        if (isElementPresent(By.id("maintable"))) {

            return;
        }
        click(By.linkText("home"));
    }

    public void goToAddNewContactHeader() {

        wd.findElement(By.linkText("add new")).click();
    }

//    public void waitElement(String text) {
//
//        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(text)));
//    }
}
