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

import java.util.concurrent.TimeUnit;

public class Topic_12_fixedPopup {

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
    public void TC_01_fixedPopup() {
    driver.get("https://ngoaingu24h.vn/");
    }

    @Test
    public void TC_02_Rightclick() {


    }

    @Test
    public void TC_03_dragDrop1() {

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void jsClick(By by) {
        javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(by));
    }
}
