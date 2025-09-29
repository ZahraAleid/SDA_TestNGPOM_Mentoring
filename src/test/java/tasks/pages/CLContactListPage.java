package tasks.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tasks.utilities.Driver;
import tasks.utilities.DriverSingleton;

public class CLContactListPage {

    public CLContactListPage(){
        PageFactory.initElements(DriverSingleton.getDriver(), this);
    }

    @FindBy(id = "logout")
    public WebElement logout;

}