package assignemnt.pages;


import assignemnt.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FluentWebTablePage {


    private WebDriverWait wait;


    private By nameInput = By.id("nameInput");
    private By ageInput = By.id("ageInput");
    private By countrySelect = By.id("countrySelect");
    private By addButton = By.xpath("//button[@onclick='addRecord()']");

    public FluentWebTablePage() {
        this.wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public FluentWebTablePage enterName(String name) {
        Driver.getDriver().findElement(nameInput).clear();
        Driver.getDriver().findElement(nameInput).sendKeys(name);
        return this;
    }

    public FluentWebTablePage enterAge(String age) {
        Driver.getDriver().findElement(ageInput).clear();
        Driver.getDriver().findElement(ageInput).sendKeys(age);
        return this;
    }

    public FluentWebTablePage selectCountry(String country) {
        Select select = new Select(Driver.getDriver().findElement(countrySelect));
        select.selectByVisibleText(country);
        return this;
    }

    public FluentWebTablePage clickAdd() {
        Driver.getDriver().findElement(addButton).click();
        return this;
    }


    public FluentWebTablePage verifyRecordAdded(String name) {
        By rowLocator = By.xpath("//table//tr[td[text()='" + name + "']]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(rowLocator));
        return this;
    }


    public FluentWebTablePage deleteRecord(String name) {
        By deleteButton = By.xpath("//tr[td[text()='" + name + "']]//button[text()='Delete']");
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//td[text()='" + name + "']")));
        System.out.println("Record deleted: " + name);
        return this;
    }
}