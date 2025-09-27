package assignemnt.pages;

import assignemnt.utilities.ConfigReader;
import assignemnt.utilities.Driver;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerManagementPage {

    //CustomerManagementPage (add/delete customer elements)

    public CustomerManagementPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // Add customer form elements
    @FindBy(xpath = "//input[@ng-model='fName']")
    public WebElement fName;

    @FindBy(xpath = "//input[@ng-model='lName']")
    public WebElement lName;

    @FindBy(xpath = "//input[@ng-model='postCd']")
    public WebElement postCode;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement addBtn;

    @FindBy(xpath = "//button[@ng-click='showCust()']")
    public WebElement customersBtn;

    // Customer management table - ADD THIS LINE
    @FindBy(xpath = "//table")
    public WebElement customersTable;

    // Delete button - this will be found dynamically for each customer row
    @FindBy(xpath = "//button[@ng-click='deleteCust(cust)']")
    public WebElement deleteBtn;

    // Search functionality
    @FindBy(xpath = "//input[@ng-model='searchCustomer']")
    public WebElement searchBox;
}