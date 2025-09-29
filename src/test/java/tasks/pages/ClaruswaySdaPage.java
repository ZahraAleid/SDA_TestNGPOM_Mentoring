package tasks.pages;

import assignemnt.utilities.Driver;
import org.openqa.selenium.By;

public class ClaruswaySdaPage {

    By addRecordWebTableBy = By.xpath("//a[.='Add Record Web Table']");

    public AddRecordWebTablePage clickWebTable(){
        Driver.getDriver().findElement(addRecordWebTableBy).click();
        return new AddRecordWebTablePage();
    }
}
