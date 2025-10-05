package tasks.utilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverSingleton {

    private static WebDriver driver;

    private DriverSingleton() {
        // Private constructor to prevent instantiation
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String browserName = ConfigReader.getProperty("browser");

            switch (browserName.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new RuntimeException("Browser not supported: " + browserName);
            }

            // Handle any existing alerts before maximizing
            handleExistingAlerts();

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

        return driver;
    }

    private static void handleExistingAlerts() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception e) {
            // No alert present, continue normally
        }
    }

    public static void closeDriver() {
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