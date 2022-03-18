package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_05_Element_Part4_Login {
    Random random = new Random();

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    By emailTextbox = By.id("email");
    By passwordTextbox = By.id("pass");
    By buttonLogin = By.id("send2");
    String firstName = "Kevin";
    String middileName = "Kevin";
    String lastName = "Kevin";
    String email = "kevin" + random.nextInt(9999) + "@gmail.com";

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
    public void TC_01_Login_Empty_Email_Password() {
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        driver.findElement(buttonLogin).click();

        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");

        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");


    }

    @Test
    public void TC_02_Login_Invalid_Email() {
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        driver.findElement(emailTextbox).sendKeys("123434234@12312.123123");
        driver.findElement(passwordTextbox).sendKeys("123456");
        driver.findElement(buttonLogin).click();
        Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");

    }

    @Test
    public void TC_03_Login_Password_Below_6Characters() {
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        driver.findElement(emailTextbox).sendKeys("automation@gmail.com");
        driver.findElement(passwordTextbox).sendKeys("123");
        driver.findElement(buttonLogin).click();
        Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_04_Login_Incorect_Email_Password() {
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        driver.findElement(emailTextbox).sendKeys("automation@gmail.com");
        driver.findElement(passwordTextbox).sendKeys("123123123");
        driver.findElement(buttonLogin).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");

    }

    @Test
    public void TC_05_Create_a_NewAccount() {
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        driver.findElement(By.id("firstname")).sendKeys(firstName);
        driver.findElement(By.id("middlename")).sendKeys(middileName);
        driver.findElement(By.id("lastname")).sendKeys(lastName);
        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("confirmation")).sendKeys("123456");
        driver.findElement(By.cssSelector("button[title='Register']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.cssSelector("p.hello >strong")).getText(), "Hello, " + firstName + " " + middileName + " " + lastName + "!");
        Assert.assertTrue(driver.findElement(By.xpath("//h3[contains(text(),'Contact Information')]/parent::div/following-sibling::div/p")).getText().contains(email));
        driver.findElement(By.xpath("//span[text()='Account']")).click();
        driver.findElement(By.cssSelector("a[title='Log Out']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title img[src$='logo.png']")).isDisplayed());


    }

    @Test
    public void TC_06_Login_Valid_Email_Password() {
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        driver.findElement(emailTextbox).sendKeys(email);
        driver.findElement(passwordTextbox).sendKeys("123456");
        driver.findElement(buttonLogin).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("p.hello >strong")).getText(), "Hello, " + firstName + " " + middileName + " " + lastName + "!");
        Assert.assertTrue(driver.findElement(By.xpath("//h3[contains(text(),'Contact Information')]/parent::div/following-sibling::div/p")).getText().contains(email));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
