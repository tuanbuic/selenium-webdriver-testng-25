package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_11_Action_part2 {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    Select select;
    WebDriverWait explicitWait;
    JavascriptExecutor javascriptExecutor;
    Alert alert;
    Actions actions;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver100.exe");
        driver = new ChromeDriver();
        actions = new Actions(driver);
        explicitWait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        javascriptExecutor = (JavascriptExecutor) driver;

    }

    @Test
    public void TC_01_rightClick() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement element = driver.findElement(By.xpath("//button[text()='Double click me']"));
        actions.doubleClick(element).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_02_Rightclick() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        actions.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).click().perform();
        Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");

    }

    @Test
    public void TC_03_dragDrop1() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        actions.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droptarget"))).perform();
        Assert.assertEquals(driver.findElement(By.id("droptarget")).getText(), "You did great!");
        Assert.assertEquals(Color.fromString(driver.findElement(By.id("droptarget")).getCssValue("background-color")).asHex().toLowerCase(), "#03a9f4");
//skip
    }

    @Test
    public void TC_04_dragdrop2() {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
    }

    @Test
    public void TC_04_authenticationAlert2() {
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void jsClick(By by) {
        javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(by));
    }
}
