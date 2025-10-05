package assignemnt.pages.day03Pages;

import assignemnt.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(id = "signup")
    public WebElement signup;

    @FindBy(id = "firstName")
    public WebElement firstName;

    @FindBy(id = "lastName")
    public WebElement lastName;

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(id = "password")
    public WebElement password;

    @FindBy(id = "submit")
    public WebElement submit;

    public void signUp(String firstName, String lastName, String email, String password){
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        submit.click();
    }

}

