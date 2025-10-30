package ru.stqa.ptf.addressbook.folderSeven.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.ptf.addressbook.folderSeven.model.GroupData;
import ru.stqa.ptf.addressbook.folderSeven.model.Groups;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {

        super(wd);
    }

    public void returnToGroupPage() {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='group page']")
        )).click();
        // click(By.linkText("group page"));
    }

    public void submitGroupCreation() {

        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {

        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {

        click(By.name("new"));
    }

    public void selectGroup() {
        wd.findElement(By.name("selected[]")).click();
        //click(By.name("selected[]"));
    }

    public void selectGroupByIndex(int index) {

        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectGroupById(int id) {

        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedGroup() {

        click(By.name("delete"));
    }

    public void initGroupModification() {


        click(By.xpath("//input[@name=\"edit\"][1]"));
    }

    public void submitGroupModification() {

        click(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("group_name")));
        fillGroupForm(group);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }

    public void modify(int index, GroupData groupData) {
        selectGroupByIndex(index);
        initGroupModification();
        fillGroupForm(groupData);
        submitGroupModification();
        returnToGroupPage();
    }

    public void modify(GroupData groupData) {
        selectGroupById(groupData.getId());
        initGroupModification();
        fillGroupForm(groupData);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }

    public void delete(int index) {
        selectGroupByIndex(index);
        deleteSelectedGroup();
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroup();
        groupCache = null;
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {

        return wd.findElements(By.name("selected[]")).size();
    }

    public int count() {

        return wd.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withId(id).withName(name));
        }

        return groups;
    }

    public Set<GroupData> allAsSet() {
        Set<GroupData> groups = new HashSet<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withId(id).withName(name));
        }

        return groups;
    }

    private Groups groupCache = null;

    public Groups all() {
        if (groupCache != null) {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupData().withId(id).withName(name));
        }

        return new Groups(groupCache);
    }
}