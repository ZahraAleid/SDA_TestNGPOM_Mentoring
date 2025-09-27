package assignemnt.pages;

import assignemnt.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpenAccount {

    public OpenAccount() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "userSelect")
    public WebElement userSelect;

    @FindBy(id = "currency")
    public WebElement userCurrency;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement processBtn;
}
