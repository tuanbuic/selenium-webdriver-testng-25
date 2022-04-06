package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_09_Button_Radio_Checkbox {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    Select select;
    WebDriverWait explicitWait;
    JavascriptExecutor javascriptExecutor ;

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
    public void TC_01_fahasa() {
        driver.get("https://www.fahasa.com/customer/account/create");
        By loginButton =  By.cssSelector("button.fhs-btn-login");
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("automationfc@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("automationfc");

        Assert.assertTrue(driver.findElement(loginButton).isEnabled());
        System.out.println(driver.findElement(loginButton).getCssValue("background"));


    }

    @Test
    public void TC_02() {
        driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");


        driver.findElement(By.id("eq5")).click();

        Assert.assertTrue(driver.findElement(By.id("eq5")).isSelected());
        driver.findElement(By.id("eq5")).click();
        Assert.assertFalse(driver.findElement(By.id("eq5")).isSelected());
        driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
        if(!driver.findElement(By.id("engine3")).isSelected()) {
            driver.findElement(By.id("engine3")).click();
        }
        Assert.assertTrue(driver.findElement(By.id("engine3")).isSelected());
    }
    @Test
    public void TC_03_angular_checbox() {
        driver.get("https://material.angular.io/components/radio/examples");

        jsClick(By.xpath("//input[@value='Summer']"));

        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Summer']")).isSelected());

        driver.get("https://material.angular.io/components/checkbox/examples");
        jsClick(By.xpath("//span[contains(text(),'Checked')]//preceding-sibling::span/input"));
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Checked')]//preceding-sibling::span/input")).isSelected());
    }
    @Test
    public void TC_04_angular_googleDoc() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        By hcmRadio = By.xpath("//div[@aria-label='Hồ Chí Minh']");
        driver.findElement(hcmRadio).click();
        Assert.assertEquals(driver.findElement(hcmRadio).getAttribute("aria-checked"),"true");

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void jsClick(By by){
        javascriptExecutor.executeScript("arguments[0].click();",driver.findElement(by));
    }
}
