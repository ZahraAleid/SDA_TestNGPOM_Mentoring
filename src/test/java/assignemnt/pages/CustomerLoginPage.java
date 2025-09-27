package assignemnt.pages;

import assignemnt.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CustomerLoginPage {


//    CustomerLoginPage (customer interface elements)

    public CustomerLoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "userSelect")
    public WebElement userSelect;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginBtn;




    public void login(String username){
        new Select(userSelect).selectByVisibleText(username);
        loginBtn.click();
    }
}
