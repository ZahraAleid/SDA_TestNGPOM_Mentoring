package tasks.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tasks.utilities.DriverSingleton;

public class CLHomePage {

    public CLHomePage() {
        PageFactory.initElements(DriverSingleton.getDriver(), this);
    }

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(id = "password")
    public WebElement password;

    @FindBy(id = "submit")
    public WebElement submit;

    @FindBy(id = "error")
    public WebElement error;


}

/*
Selenium Page Factory Pattern is an extension of the Page Object Model, but it is a much more advanced model.

The Factory class makes the use of Page Objects simpler and easier
Used to initialize WebElements defined in Page Objects
We need to initialize page class objects using initElements() method

Page Factory Benefits:
Lazy Loading: Elements are located only when accessed
Clean Code: @FindBy annotation instead of driver.findElement()
Automatic Initialization: initElements() handles WebElement creation
Better Performance: Elements are found fresh each time they're accessed
 */

/*
Access Modifier Rules:
default/private: WebElement only used in Page Classes
public: Can be accessed from Test Classes

Page Factory Approach:
@FindBy(id = "username")
private WebElement usernameField;

Key Rules:
Location: WebElements in Page Classes, not Test Classes
Usage: Access through Page Class objects
Clean Code: @FindBy annotation makes code more readable
 */