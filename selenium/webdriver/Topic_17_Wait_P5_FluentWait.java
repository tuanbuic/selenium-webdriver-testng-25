package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class Topic_17_Wait_P5_FluentWait {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    Select select;
    WebDriverWait explicitWait;
    JavascriptExecutor javascriptExecutor;
    Alert alert;
    Actions actions;
    FluentWait<WebDriver> fluentDriver;
    FluentWait<WebElement> FluentElement;

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
        fluentDriver = new FluentWait<WebDriver>(driver);
        fluentDriver.withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
        WebElement feelingLuckyButton = (WebElement) fluentDriver.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//input[@name='btnI-fail']"));
            }
        });

    }
    @Test
    public void TC_02_fluentWait() throws InterruptedException {

        driver.get("https://automationfc.github.io/dynamic-loading/");
        fluentDriver = new FluentWait<WebDriver>(driver);
        driver.findElement(By.cssSelector("div#start>button")).click();
//        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
        fluentDriver.withTimeout(Duration.ofSeconds(6))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .until(webDriver -> {
                    Boolean a =  driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed();
                    System.out.println(a);
                    return a;
                });
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void jsClick(By by) {
        javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(by));
    }

    public String getTimeNow() {
        Date date = new Date();
        return date.toString();
    }
}