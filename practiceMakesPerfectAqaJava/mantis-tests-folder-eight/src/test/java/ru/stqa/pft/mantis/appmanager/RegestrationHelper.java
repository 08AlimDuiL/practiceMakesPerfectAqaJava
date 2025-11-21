package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;


public class RegestrationHelper extends HelperBase {

    // private WebDriver wd; убрали в папке 8.6

    private static final By SUBMIT = By.xpath("//input[@type='submit']");
    private static final By PASSWORD = By.id("password");
    private static final By PASSWORD_CONFIRM = By.id("password-confirm");
    private static final By USER_NAME = By.id("username");
    private static final By EMAIL_FIELD = By.id("email-field");

    public RegestrationHelper(ApplicationManager app) {

        super(app);
        // wd = app.getDriver();//ленивая инициализация  // убрали в папке 8.6
    }

    public void start(String userName, String email) throws InterruptedException {

        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        //wd.findElement(By.name("username")).sendKeys(); убрали в папке 8.6
        type(USER_NAME, userName);
        type(EMAIL_FIELD, email);
        click(SUBMIT);
    }

    public void finish(String confirmationLink, String password) throws InterruptedException {

        wd.get(confirmationLink);
        type(PASSWORD, password);
        type(PASSWORD_CONFIRM, password);
        click(SUBMIT);
    }
}