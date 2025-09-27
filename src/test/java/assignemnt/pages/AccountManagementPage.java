package assignemnt.pages;

import assignemnt.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountManagementPage {

    public AccountManagementPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//button[@ng-click='customer()']")
    public WebElement customerLoginBtn;

    @FindBy(xpath = "//button[@ng-click='manager()']")
    public WebElement managerLoginBtn;

    @FindBy(xpath = "//button[@ng-click='home()']")
    public  WebElement homeBtn;
}

