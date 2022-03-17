package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Topic_05_Element_Part3 {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    By fullNameTextbox = By.id("txtFirstname");
    By EmailAddress = By.id("txtEmail");
    By confirmEmailAddress = By.id("txtCEmail");
    By txtPassword = By.id("txtPassword");
    By txtCPassword = By.id("txtCPassword");
    By txtPhone = By.id("txtPhone");

    @BeforeMethod
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }

    @Test
    public void TC_01_Register_Empty_Data() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

    }

    @Test
    public void TC_02_Register_Invalid_Email() {
        driver.findElement(EmailAddress).sendKeys("123@456@789");
        driver.findElement(confirmEmailAddress).sendKeys("123@456@789");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
    }

    @Test
    public void TC_03_Register_Incorrect_Confirm_Email() {
        driver.findElement(fullNameTextbox).sendKeys("Tuan Bui");
        driver.findElement(EmailAddress).sendKeys("tuanbui@gmail.com");
        driver.findElement(confirmEmailAddress).sendKeys("tuanbui@gmail.net");
        driver.findElement(txtPassword).sendKeys("Abcd1234!");
        driver.findElement(txtCPassword).sendKeys("Abcd1234!");
        driver.findElement(txtPhone).sendKeys("0907231123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");


    }

    @Test
    public void TC_04_Register_Password_LowerThan6Characters() {
        driver.findElement(fullNameTextbox).sendKeys("Tuan Bui");
        driver.findElement(EmailAddress).sendKeys("tuanbui@gmail.com");
        driver.findElement(confirmEmailAddress).sendKeys("tuanbui@gmail.com");
        driver.findElement(txtPassword).sendKeys("Abcd");
        driver.findElement(txtCPassword).sendKeys("Abcd");
        driver.findElement(txtPhone).sendKeys("0907231123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void TC_05_Register_Incorrect_Confirm_Password() {
        driver.findElement(fullNameTextbox).sendKeys("Tuan Bui");
        driver.findElement(EmailAddress).sendKeys("tuanbui@gmail.com");
        driver.findElement(confirmEmailAddress).sendKeys("tuanbui@gmail.com");
        driver.findElement(txtPassword).sendKeys("Abcd1234!");
        driver.findElement(txtCPassword).sendKeys("Abcd1567");
        driver.findElement(txtPhone).sendKeys("0907231123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");

    }

    @Test
    public void TC_06_Register_Invalid_Phone() {
        driver.findElement(fullNameTextbox).sendKeys("Tuan Bui");
        driver.findElement(EmailAddress).sendKeys("tuanbui@gmail.com");
        driver.findElement(confirmEmailAddress).sendKeys("tuanbui@gmail.com");
        driver.findElement(txtPassword).sendKeys("Abcd1234!");
        driver.findElement(txtCPassword).sendKeys("Abcd1234!");
        driver.findElement(txtPhone).sendKeys("0909");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

    }
    @AfterMethod
    public void afterClass() {
        driver.quit();
    }
}
