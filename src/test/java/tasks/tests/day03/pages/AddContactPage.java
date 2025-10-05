package tasks.tests.day03.pages;

import tasks.tests.day03.utilis.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddContactPage {

    public AddContactPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "firstName")
    public WebElement firstName;

    @FindBy(id = "lastName")
    public WebElement lastName;

    @FindBy(id = "birthdate")
    public WebElement birthdate;

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(id = "phone")
    public WebElement phone;

    @FindBy(id = "street1")
    public WebElement street1;

    @FindBy(id = "street2")
    public WebElement street2;

    @FindBy(id = "city")
    public WebElement city;

    @FindBy(id = "stateProvince")
    public WebElement stateProvince;

    @FindBy(id = "postalCode")
    public WebElement postalCode;

    @FindBy(id = "country")
    public WebElement country;

    @FindBy(id = "submit")
    public WebElement submit;

    public void addContact(String firstName, String lastName, String birthdate, String email, String phone,
                           String street1, String street2, String city, String stateProvince, String postalCode, String country) {

        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.birthdate.sendKeys(birthdate);
        this.email.sendKeys(email);
        this.phone.sendKeys(phone);
        this.street1.sendKeys(street1);
        this.street2.sendKeys(street2);
        this.city.sendKeys(city);
        this.stateProvince.sendKeys(stateProvince);
        this.postalCode.sendKeys(postalCode);
        this.country.sendKeys(country);
        submit.click();
    }

}
