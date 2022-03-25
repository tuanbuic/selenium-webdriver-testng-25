package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.channels.SelectableChannel;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_07_Default_Dropdown {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    Select select;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get("http://live.techpanda.org/");
    }

    @Test
    public void TC_01() {
        //Not able to demo


    }

    @Test
    public void TC_02_noComm() {
        driver.get("https://demo.nopcommerce.com/register");
        driver.findElement(By.xpath("//a[contains(text(),'Register')]")).click();
        driver.findElement(By.id("gender-male")).click();
        driver.findElement(By.id("FirstName")).sendKeys("Kevin");
        driver.findElement(By.id("LastName")).sendKeys("Kevin");

        select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
        select.selectByVisibleText("10");
        select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
        select.selectByVisibleText("October");
        select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
        select.selectByVisibleText("1995");
        driver.findElement(By.id("Email")).sendKeys("kevin1234@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("Abcd1234!");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("Abcd1234!");

    }

    @Test
    public void TC_03() {

    }

    @Test
    public void TC_04() {

    }

    @Test
    public void TC_05() {

    }

    @Test
    public void TC_06() {
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
