package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_08_Custom_Dropdown {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    Select select;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver,10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @BeforeMethod
    public void beforeMethod(){
    }
    public void selectItemInCustomDropdownList(String parentLocator,String childLocator, String expectedTestItem){
        driver.findElement(By.cssSelector(parentLocator)).click();
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(childLocator)));
        List<WebElement> allTimes = driver.findElements(By.cssSelector(childLocator));
        for(WebElement e: allTimes){
            String actualText = e.getText();
            if(e.getText().equals(expectedTestItem)){
                e.click();
                break;
            }
        }

    }    @Test
    public void TC_01() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemInCustomDropdownList("span#number-button > span.ui-selectmenu-icon","ul#number-menu div","2");
        selectItemInCustomDropdownList("span#number-button > span.ui-selectmenu-icon","ul#number-menu div","5");
        selectItemInCustomDropdownList("span#number-button > span.ui-selectmenu-icon","ul#number-menu div","19");
        selectItemInCustomDropdownList("div.selection","ul#number-menu div","19");
    }

    @Test
    public void TC_02_noComm() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
