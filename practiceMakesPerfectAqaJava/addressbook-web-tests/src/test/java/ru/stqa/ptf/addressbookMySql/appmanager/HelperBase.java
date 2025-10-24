package ru.stqa.ptf.addressbookMySql.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {

        this.wd = wd;
    }

    protected void click(By locator) {

        wd.findElement(locator).click();
    }

    protected void clickDropdownAndType(By locator, String text) {
        if (text != null && !text.trim().isEmpty()) {
            click(locator);
            new Select(wd.findElement(locator)).selectByVisibleText(text);
        }
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals((existingText))) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    /**
     * Folder 6.1.
     */
    protected void attach(By locator, File file) {
        if (file != null) {
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();

            return true;
        } catch (NoAlertPresentException e) {

            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}