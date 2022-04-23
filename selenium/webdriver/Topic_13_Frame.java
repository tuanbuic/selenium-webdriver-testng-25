package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_13_Frame {

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
    public void TC_01_iFrame() throws InterruptedException {
        driver.get("https://kyna.vn/");
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content >iframe")));
        Assert.assertEquals(driver.findElement(By.xpath("//a[contains(text(),'Kyna.vn')]/parent::div/following-sibling::div")).getText(),"167K likes");
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.id("cs_chat_iframe")));
        driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
        driver.findElement(By.cssSelector("input.input_name")).sendKeys("Test123");
        driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0908000222");
        driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("0908000222");
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
        driver.findElement(By.cssSelector("button.search-button")).click();
        List<WebElement> list = driver.findElements(By.cssSelector("div.content > h4"));
        list.forEach((webElement -> {
            Assert.assertTrue(webElement.getText().contains("Excel"));
        }));


    }

    @Test
    public void TC_02_Frame() throws InterruptedException {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        driver.switchTo().frame("login_page");
        driver.findElement(By.name("fldLoginUserId")).sendKeys("AutomationTesting");
        driver.findElement(By.cssSelector("a.login-btn")).click();

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
