package assignemnt.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import assignemnt.utilities.Driver;

public class CLLoginPage {

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(id = "signup")
    private WebElement signupButton;


    public CLLoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public void login(String email, String password) {
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        submitButton.click();
    }

    //signup
    public void signup(String firstName, String lastName, String email, String password) {
        signupButton.click();
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        submitButton.click();
    }


}