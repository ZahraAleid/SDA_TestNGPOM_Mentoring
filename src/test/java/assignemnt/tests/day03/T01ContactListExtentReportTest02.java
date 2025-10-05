package assignemnt.tests.day03;

import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;
import assignemnt.pages.day03Pages.AddContactPage;
import assignemnt.pages.day03Pages.ContactListPage;
import assignemnt.pages.day03Pages.LoginPage;
import assignemnt.utilities.Driver;
import tasks.utilities.CaptureScreenshot;
import tasks.utilities.ConfigReader;
import tasks.utilities.ExtentReportManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Locale;

public class T01ContactListExtentReportTest02 {
    /*
Target: https://thinking-tester-contact-list.herokuapp.com/
Test Scenario:
1. Navigate to the application
2. Create a new user account
3. Login with the created user
4. Add 5 different contacts
5. Assert that all contacts are properly added and displayed
Page Objects Needed:
- LoginPage (registration and login elements)
- ContactListPage (contact management elements)
- ContactListPage (contact form elements)
Assertions:
- Verify successful user registration
- Verify successful login
- Verify each contact is added correctly
- Verify total contact count equals 5
Reporting Requirements:
- Use ExtentReports
- Add .info() logs for each major step
- Use .pass() for successful assertions
- Use .fail() for failed assertions with screenshots
- Add system information (Browser, Environment, Tester)
- Generate report with timestamp in filename
*/

    @Test
    void getDriverTest(){
        ExtentReportManager.createTest("Create Five Contacts Test Started");

        LoginPage loginPage = new LoginPage();
        
        ContactListPage contactListPage = new ContactListPage();
        AddContactPage addContactPage = new AddContactPage();
        Faker faker = new Faker();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

//        Test Scenario:
//        1. Navigate to the application
        try {
            Driver.getDriver().get(ConfigReader.getProperty("cl_url"));
            ExtentReportManager.log(Status.PASS,"User Navigates to Contactlist Page");
        } catch (Exception e){
            ExtentReportManager.log(Status.FAIL,"User failed to navigate Contactlist Page");
        }

//        2. Create a new user account
//        3. Login with the created user
        loginPage.signup.click();
        ExtentReportManager.log(Status.INFO,"User is Signing Up To the Application");

        loginPage.signUp(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.internet().password());

        if (contactListPage.logout.isDisplayed()){
            ExtentReportManager.log(Status.PASS,"User created succesfully");
        } else {
            ExtentReportManager.log(Status.FAIL,"User is not registered");
        }
        Assert.assertTrue(contactListPage.logout.isDisplayed()); // Assert successful user registration


//        4. Add 5 different contacts
        for (int i = 0; i<5; i++){
            Driver.getDriver().navigate().refresh();
            contactListPage.addContact.click();
            ExtentReportManager.log(Status.INFO,"Creating contact number : "+ i );
            addContactPage.addContact(faker.name().firstName(),
                    faker.name().lastName(),
                    new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(faker.date().birthday()),
                    faker.internet().emailAddress(),
                    faker.phoneNumber().cellPhone().replaceAll("[^0-9]", ""),
                    faker.address().streetAddress(),
                    faker.address().streetAddress(),
                    faker.address().city(),
                    faker.address().state(),
                    faker.address().zipCode(),
                    faker.country().name());

            // Verify each contact is added correctly
            wait.until(ExpectedConditions.visibilityOf(contactListPage.addContact));

            if(contactListPage.addContact.isDisplayed()) {
                ExtentReportManager.log(Status.PASS,"Contact number "+i+" is created succesfully");
            } else {
                ExtentReportManager.log(Status.FAIL,"Contact number "+i+" is NOT created ");
            }
            Assert.assertTrue(contactListPage.addContact.isDisplayed());

        }

//        5. Assert that all contacts are properly added and displayed
        int rowCount = contactListPage.dataCount.size();
        if (rowCount==6){
            ExtentReportManager.log(Status.PASS,"Five contacts are creted succesfully");
        } else {
            ExtentReportManager.log(Status.FAIL,"Contact are not created ");
            ExtentReportManager.getTest().addScreenCaptureFromPath(CaptureScreenshot.takeScreenshot(Driver.getDriver(),"name"));
        }
        Assert.assertEquals(rowCount,6);


    }
}
