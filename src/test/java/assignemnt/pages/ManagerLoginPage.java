package assignemnt.pages;

import assignemnt.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerLoginPage {

    public ManagerLoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//button[@ng-class='btnClass1']")
    public WebElement addCustomerBtn;

    @FindBy(xpath = "//button[@ng-class='btnClass2']")
    public WebElement openAccountBtn;

    @FindBy(xpath = "//button[@ng-class='btnClass3']")
    public WebElement customersBtn;


}
