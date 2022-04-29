package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Topic_17_Wait_P1_Element_Status {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    Select select;
    WebDriverWait explicitWait;
    JavascriptExecutor javascriptExecutor;
    Alert alert;
    Actions actions;
    int a= 10;
    String portugalName = "portugal.png";
    String spainName = "spain.png";
    String swissName = "swiss.png";
    String imageFilePath= projectPath + File.separator+"Uploadfiles"+File.separator;
    String portugalFilePath =imageFilePath+portugalName;
    String spainFilePath =imageFilePath+spainName;
    String swissFilePath =imageFilePath+swissName;
    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver100.exe");
        driver = new ChromeDriver();
        actions = new Actions(driver);
        explicitWait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        javascriptExecutor = (JavascriptExecutor) driver;
        driver.get("https://facebook.com");

    }

    @Test
    public void TC_01_Visible() throws InterruptedException {
        //visible : co tren UI va co trong DOM

         explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
    }

    @Test
    public void TC_02_Invisible() throws InterruptedException {
        //Invisible: không có trên UI và có trong dom(không bắt buộc)
        //KLetts quả như nhau nhưng thời gian chạy mỗi case là khác nhau
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
    }

    @Test
    public void TC_03_Presence() {
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));

    }   @Test
    public void TC_04_staleness() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void jsClick(By by) {
        javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(by));
    }
}
