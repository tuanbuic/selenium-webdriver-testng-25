package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_10_Alert {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    Select select;
    WebDriverWait explicitWait;
    JavascriptExecutor javascriptExecutor ;
    Alert alert;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver,10);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        javascriptExecutor=(JavascriptExecutor) driver;

    }
    @Test
    public void TC_01_() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.cssSelector("button[onclick='jsAlert()']")).click();
        alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"I am a JS Alert");
        alert.accept();

    }

    @Test
    public void TC_02() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.cssSelector("button[onclick='jsConfirm()']")).click();
        alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"I am a JS Confirm");
        alert.dismiss();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked: Cancel");

    }
    @Test
    public void TC_03_promtAlert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.cssSelector("button[onclick='jsPrompt()']")).click();
        alert = driver.switchTo().alert();

    }
    @Test
    public void TC_04_authenticationAlert() throws InterruptedException {
        String username = "admin";
        String password = "admin";
        driver.get("http://"+username+":"+password+"@"+"the-internet.herokuapp.com/basic_auth");
    }
    @Test
    public void TC_04_authenticationAlert2() {
        String username = "admin";
        String password = "admin";
        driver.get("http://the-internet.herokuapp.com");
        String basicLink =  driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        String [] basicAuthen = basicLink.split("//");
        basicLink = basicAuthen[0]+"//"+username+":"+password+"@"+basicAuthen[1];
        driver.get(basicLink);
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void jsClick(By by){
        javascriptExecutor.executeScript("arguments[0].click();",driver.findElement(by));
    }
}
