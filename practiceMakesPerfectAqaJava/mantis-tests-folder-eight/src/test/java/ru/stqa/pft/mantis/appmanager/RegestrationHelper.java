package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;


public class RegestrationHelper extends HelperBase {

    // private WebDriver wd; убрали в папке 8.6

    public RegestrationHelper(ApplicationManager app) {

        super(app);
        // wd = app.getDriver();//ленивая инициализация  // убрали в папке 8.6
    }

    public void start(String userName, String email) throws InterruptedException {

        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        //wd.findElement(By.name("username")).sendKeys(); убрали в папке 8.6
        type(By.id("username"), userName);
        type(By.id("email-field"), email);
        click(By.xpath("//input[@type='submit']"));
    }

    public void finish(String confirmationLink, String password) throws InterruptedException {

        wd.get(confirmationLink);
        Thread.sleep(30000);
        type(By.id("password"), password);
        Thread.sleep(3000);
        type(By.id("password-confirm"), password);
        Thread.sleep(3000);
        click(By.xpath("//input[@type='submit']"));
    }
}