package assignemnt.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import assignemnt.utilities.Driver;

import java.util.List;

public class CLContactListPage {

    @FindBy(id = "logout")
    public WebElement logout;

    @FindBy(id = "add-contact")
    public WebElement addContact;

    @FindBy(xpath = "//table/tr")
    public List<WebElement> myTableRows;


    public CLContactListPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

}