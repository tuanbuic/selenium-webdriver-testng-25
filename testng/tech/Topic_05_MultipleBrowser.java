package tech;

import com.beust.jcommander.Parameter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_05_MultipleBrowser {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    By emailTextbox= By.xpath("//*[@id='email']");
    By passwordTextBox= By.xpath("//*[@id='pass']");
    By loginButton= By.xpath("//*[@id='send2']");
    @Parameters({"browser", "env"})
    @BeforeClass
    public void beforeClass(String browserName,String env){
        switch(browserName){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver100.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Wrong browser");
        }
        switch(env){
            case "dev":
               driver.get("http://dev.techpanda.com");
                break;
            case "live":
                driver.get("http://live.techpanda.com");
                break;
            default:
                System.out.println("Wrong env");
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @Test
    public void Test1(){

    }
}
