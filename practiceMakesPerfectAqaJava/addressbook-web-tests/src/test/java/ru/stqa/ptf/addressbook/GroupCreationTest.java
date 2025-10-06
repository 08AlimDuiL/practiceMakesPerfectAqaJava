package ru.stqa.ptf.addressbook;

import java.time.Duration;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class GroupCreationTest {
    private WebDriver wd;
    private WebDriverWait wait;


    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wait = new WebDriverWait(wd, Duration.ofSeconds(10));
        wd.get("http://127.0.0.1/addressbook/");
        login("admin", "secret");
    }

    private void login(String userName, String password) {
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(userName);
        wd.findElement(By.id("LoginForm")).click();
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @Test
    public void testGroupCreation() throws Exception {
        goToGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("test1", "test2", "test3"));
        submitGroupCreation();
        returnToGroupPage();
        logout();
    }

    @Test(dependsOnMethods = "testGroupCreation")
    public void testCreationContact() throws Exception {
        goToAddNewContact();
        fillFormContact(new ContactsData(
                "Vasilii",
                "Ivanovich",
                "Ivanov",
                "Vasya",
                "Neo",
                "address",
                "1112233",
                "+79113334455",
                "work",
                "no",
                "1112233@mail.ru",
                "2",
                "January",
                "2000",
                "test1")
        );
        goToHomePage();
        logout();
    }

    private void fillFormContact(ContactsData contactsData) {
        wd.findElement(By.name("firstname")).click();
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys(contactsData.getName());
        wd.findElement(By.name("middlename")).click();
        wd.findElement(By.name("middlename")).clear();
        wd.findElement(By.name("middlename")).sendKeys(contactsData.getMiddleName());
        wd.findElement(By.name("lastname")).click();
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(contactsData.getLastName());
        wd.findElement(By.name("nickname")).click();
        wd.findElement(By.name("nickname")).clear();
        wd.findElement(By.name("nickname")).sendKeys(contactsData.getNickName());
        wd.findElement(By.name("company")).click();
        wd.findElement(By.name("company")).clear();
        wd.findElement(By.name("company")).sendKeys(contactsData.getCompany());
        wd.findElement(By.name("address")).click();
        wd.findElement(By.name("address")).clear();
        wd.findElement(By.name("address")).sendKeys(contactsData.getAddress());
        wd.findElement(By.name("home")).click();
        wd.findElement(By.name("home")).clear();
        wd.findElement(By.name("home")).sendKeys(contactsData.getHomePhoneNumber());
        wd.findElement(By.name("mobile")).click();
        wd.findElement(By.name("mobile")).clear();
        wd.findElement(By.name("mobile")).sendKeys(contactsData.getMobilePhoneNumber());
        wd.findElement(By.name("work")).click();
        wd.findElement(By.name("work")).clear();
        wd.findElement(By.name("work")).sendKeys(contactsData.getWork());
        wd.findElement(By.name("fax")).click();
        wd.findElement(By.name("fax")).clear();
        wd.findElement(By.name("fax")).sendKeys(contactsData.getFax());
        wd.findElement(By.name("email")).click();
        wd.findElement(By.name("email")).clear();
        wd.findElement(By.name("email")).sendKeys(contactsData.getEmail());
        wd.findElement(By.name("bday")).click();
        new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactsData.getBirthDay());
        wd.findElement(By.name("theform")).click();
        new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactsData.getBirthMonth());
        wd.findElement(By.name("byear")).click();
        wd.findElement(By.name("byear")).clear();
        wd.findElement(By.name("byear")).sendKeys(contactsData.getBirthYear());
        wd.findElement(By.name("new_group")).click();
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactsData.getGroup());
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    private void goToHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }

    private void goToAddNewContact() {
        wd.findElement(By.linkText("add new")).click();
    }

    private void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    private void returnToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    private void submitGroupCreation() {
        wd.findElement(By.name("submit")).click();
    }

    private void fillGroupForm(GroupData groupData) {
        wd.findElement(By.name("group_name")).click();
        wd.findElement(By.name("group_name")).clear();
        wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
        wd.findElement(By.name("group_header")).click();
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        wd.findElement(By.name("group_footer")).click();
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    private void initGroupCreation() {
        wd.findElement(By.name("new")).click();
    }

    private void goToGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        wd.quit();
    }

    private boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}