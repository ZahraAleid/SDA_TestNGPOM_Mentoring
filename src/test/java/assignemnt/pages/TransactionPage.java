package assignemnt.pages;

import assignemnt.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TransactionPage {

    public TransactionPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // Transaction navigation buttons
    @FindBy(xpath = "//button[@ng-class='btnClass2']")
    public WebElement depositBtn;

    @FindBy(xpath = "//button[@ng-class='btnClass3']")
    public WebElement withdrawlBtn;


    // Amount input field
    @FindBy(xpath = "//input[@ng-model='amount']")
    public WebElement amountInput;

    // Submit buttons
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement depositSubmitBtn;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement withdrawSubmitBtn;

    // Balance and account info
    @FindBy(xpath = "//strong[2]")
    public WebElement balanceLabel;

    // Success/Error messages
    @FindBy(xpath = "//span[@ng-show='message']")
    public WebElement message;

    // Logout button
    @FindBy(xpath = "//button[@ng-click='byebye()']")
    public WebElement logoutBtn;

    // Account details
    @FindBy(xpath = "//strong[3]")
    public WebElement currency;
}