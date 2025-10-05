package tasks.tests.day03.utilis;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tasks.utilities.ConfigReader;

import java.time.Duration;

//Singleton Driver class
public class Driver {

    private static WebDriver driver; // w/o initialization it's null

    private Driver(){//No one can create an Object from this class
    }

    //Create a new WebDriver if it doesn't exist
    public static WebDriver getDriver(){

        if (driver == null) {

            switch (ConfigReader.getProperty("browser").toLowerCase()){

                case "firefox":
                    driver = new FirefoxDriver();
                    break;

                case "edge":
                    driver = new EdgeDriver();
                    break;

                case "headless":
                    driver = new ChromeDriver((new ChromeOptions().addArguments("--headless")));
                    break;

                default:
                    driver = new ChromeDriver();
            }
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;

    }

    //safely close the driver
    public static void closeDriver(){
        try { //don't use in real world!!
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    /*
    Multi-Browser Support: Chrome, Firefox, Safari, Headless Chrome
    Configuration-Driven: Browser type from properties file
    Implicit Wait: Built-in timeout management
    Window Management: Automatic maximization
    Proper Cleanup: closeDriver() method for resource management
     */
}
