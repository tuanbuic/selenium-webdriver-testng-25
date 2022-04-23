package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_12_RandomPopup {

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
    public void TC_01_RandomInDOM() throws InterruptedException {

        driver.get("https://vnk.edu.vn/");
            Thread.sleep(20000);
        if(driver.findElement(By.cssSelector("div.tcb-col > div")).isDisplayed()){
            driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
            Thread.sleep(3000);
        }
        driver.findElement(By.xpath("//a[contains(text(),'Liên hệ')]")).click();
    }

    @Test
    public void TC_02() throws InterruptedException {
        driver.get("https://dehieu.vn/");
        Thread.sleep(20000);
        if(driver.findElement(By.cssSelector("div.tcb-col > div")).isDisplayed()){
            driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
            Thread.sleep(3000);
        }
        driver.findElement(By.xpath("//a[contains(text(),'Liên hệ')]")).click();

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
