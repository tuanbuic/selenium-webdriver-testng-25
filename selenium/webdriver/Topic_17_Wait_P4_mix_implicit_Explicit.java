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
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_17_Wait_P4_mix_implicit_Explicit {
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
        driver.get("https://www.facebook.com/");
        By emailID = By.id("email123123");
        //1- Implicit < explicit
       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        explicitWait = new WebDriverWait(driver, 5);
//        System.out.println("Start imp = "+getTimeNow());
////        try {
////            driver.findElement(emailID).isDisplayed();
////        }catch (Exception e){
////            System.out.println("End imp = "+getTimeNow());
////        }
//
        System.out.println("start exp = "+getTimeNow());
        try{explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailID));}
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("End exp = "+getTimeNow());
        //2- Implicit = explicit
        //3 Implicit > explicit


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void jsClick(By by) {
        javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(by));
    }
    public String getTimeNow(){
    Date date = new Date();
    return date.toString();
    }
}