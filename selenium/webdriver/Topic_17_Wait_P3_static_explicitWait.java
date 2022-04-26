package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_17_Wait_P3_static_explicitWait {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    Select select;
    WebDriverWait explicitWait;
    JavascriptExecutor javascriptExecutor;
    Alert alert;
    Actions actions;
    int a = 10;
    String portugalName = "portugal.png";
    String spainName = "spain.png";
    String swissName = "swiss.png";
    String imageFilePath = projectPath + File.separator + "Uploadfiles" + File.separator;
    String portugalFilePath = imageFilePath + portugalName;
    String spainFilePath = imageFilePath + spainName;
    String swissFilePath = imageFilePath + swissName;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver100.exe");
        driver = new ChromeDriver();
        actions = new Actions(driver);
        explicitWait = new WebDriverWait(driver, 10);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        javascriptExecutor = (JavascriptExecutor) driver;

    }

    @Test
    public void TC_01_Equal() throws InterruptedException {

        explicitWait = new WebDriverWait(driver, 5);
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
//        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void TC_02_Less() throws InterruptedException {
        explicitWait = new WebDriverWait(driver, 3);
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
//        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_03_More() {
        explicitWait = new WebDriverWait(driver, 30);
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
//        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void TC_04_Ajax() {
        explicitWait = new WebDriverWait(driver, 30);
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
        WebElement selectDate = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
        Assert.assertEquals(selectDate.getText(), "No Selected Dates to display.");
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='11']"))).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1'] > div.raDiv")));
        Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "Monday, April 11, 2022");
    }

    @Test
    public void TC_05_Upload() throws InterruptedException {
        explicitWait = new WebDriverWait(driver, 30);
        driver.get("https://gofile.io/?t=uploadFiles");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(portugalFilePath + "\n" + spainFilePath + "\n" + swissFilePath);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.progress")));
        Thread.sleep(5000);
        Assert.assertEquals(driver.findElement(By.cssSelector("h5")).getText(),"Your files have been successfully uploaded");

    }

    public void switchToWindowByTitle(String expectedTtile) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String id : allWindows) {
            driver.switchTo().window(id);
            if (driver.getTitle().contains(expectedTtile)) {
                break;
            }
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void jsClick(By by) {
        javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(by));
    }
}
