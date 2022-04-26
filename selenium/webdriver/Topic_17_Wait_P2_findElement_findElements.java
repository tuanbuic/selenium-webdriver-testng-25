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

import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_17_Wait_P2_findElement_findElements {
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        javascriptExecutor = (JavascriptExecutor) driver;

    }

    @Test
    public void TC_01_Find_Element() throws InterruptedException {
        // Implicit wait chỉ ảnh hưởng đến 2 hàm findElement và findElements
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//input[@name='firstname']"));

    }

    @Test
    public void TC_02_Find_Elements() throws InterruptedException {

    }

    @Test
    public void TC_03_implicitWait() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

    }

    public void switchToWindowByTitle(String expectedTtile) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String id : allWindows) {
            driver.switchTo().window(id);
            if(driver.getTitle().contains(expectedTtile)){
                break;
            }
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void jsClick(By by) {
        javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(by));
    }
}
