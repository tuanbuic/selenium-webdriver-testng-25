package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_11_Action_part1 {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    Select select;
    WebDriverWait explicitWait;
    JavascriptExecutor javascriptExecutor;
    Alert alert;
    Actions actions;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        actions = new Actions(driver);
        explicitWait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        javascriptExecutor = (JavascriptExecutor) driver;

    }

    @Test
    public void TC_01_() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        actions.moveToElement(driver.findElement(By.id("age"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");

    }

    @Test
    public void TC_02() {
        driver.get("http://www.myntra.com/");
        actions.moveToElement(driver.findElement(By.cssSelector("a[data-group='kids']"))).perform();
        driver.findElement(By.xpath("//a[contains(text(),'Home & Bath')]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(),"Kids Home Bath");
    }

    @Test
    public void TC_03() {
//skip
    }

    @Test
    public void TC_04_clickanHold()  {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> elements = driver.findElements(By.cssSelector("ol >li "));
        actions.clickAndHold(elements.get(0)).moveToElement(elements.get(3)).release().perform();

    }

    @Test
    public void TC_04_authenticationAlert2() {
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void jsClick(By by) {
        javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(by));
    }
}
