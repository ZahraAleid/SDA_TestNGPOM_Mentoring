package assignemnt.tests.day01;


import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import assignemnt.pages.CLAddContactPage;
import assignemnt.pages.CLContactListPage;
import assignemnt.pages.CLLoginPage;
import assignemnt.utilities.ConfigReader;
import assignemnt.utilities.Driver;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.Locale;


public class T01_ContactListApplicationTest {

    /*
    HomeWork For Weekend :1
    Example 1: Contact List Application Test
    Target: https://thinking-tester-contact-list.herokuapp.com/

    Test Scenario:
        Navigate to the application
        Create a new user account
        Login with the created user
        Add 5 different contacts
        Assert that all contacts are properly added and displayed

    Page Objects Needed:
        LoginPage (registration and login elements)
        ContactListPage (contact management elements)
        AddContactPage (contact form elements)

    Assertions:
        Verify successful user registration
        Verify successful login
        Verify each contact is added correctly
        Verify total contact count equals 5
        //
     */

    @Test
    public void contactListApplicationTest() throws InterruptedException {

        CLLoginPage clLoginPage = new CLLoginPage();
        CLContactListPage clContactListPage = new CLContactListPage();
        CLAddContactPage addContactPage = new CLAddContactPage();

        Driver.getDriver().get(ConfigReader.getProperty("cl_url"));

        Faker faker = new Faker();

        String userEmail = faker.internet().emailAddress();
        String userPassword = faker.internet().password();

        clLoginPage.signup(faker.name().firstName(), faker.name().lastName(), userEmail, userPassword);

        Assert.assertTrue(clContactListPage.logout.isDisplayed());

        clContactListPage.logout.click();

        clLoginPage.login(userEmail, userPassword);

        Assert.assertTrue(clContactListPage.logout.isDisplayed());

        for (int i = 0; i < 5; i++) {


            try {
                Thread.sleep(2000);
                Assert.assertEquals(clContactListPage.myTableRows.size(), i);
                clContactListPage.addContact.click();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String birthdate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(faker.date().birthday());
            String email = faker.internet().emailAddress();
            String phone = faker.phoneNumber().cellPhone().replaceAll("[^0-9]","");
            String street1 = faker.address().streetAddress();
            String street2 = faker.address().streetAddress();
            String city = faker.address().city();
            String state = faker.address().state();
            String zip = faker.address().zipCode();
            String countryCode = faker.address().countryCode();

            addContactPage.addContact(firstName, lastName, birthdate, email, phone, street1, street2, city, state, zip, countryCode);

            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5))
                    .until(ExpectedConditions.textToBePresentInElementLocated(
                            By.xpath("//table"), email));


            String tableText = Driver.getDriver().findElement(By.xpath("//table")).getText();
            Assert.assertTrue(tableText.contains(email),
                    "Table does not contain expected email: " + email +
                            "\nBut table text was:\n" + tableText);
        }

        Assert.assertEquals(clContactListPage.myTableRows.size(), 5);

        Driver.closeDriver();

    }
}