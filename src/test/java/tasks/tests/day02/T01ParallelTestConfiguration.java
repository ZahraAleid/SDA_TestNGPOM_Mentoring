package tasks.tests.day02;

import org.testng.annotations.Test;
import tasks.pages.CLContactListPage;
import tasks.pages.CLHomePage;
import tasks.utilities.ConfigReader;
import tasks.utilities.DriverSingleton;

public class T01ParallelTestConfiguration {

    /*
    Task 1: Parallel Test Configuration
        Objective: Create a parallel test suite that runs login tests on multiple browsers simultaneously.
        Requirements:
        Create a TestNG XML file with parallel execution at the method level
        Set thread count to 3
        Include at least 2 test classes: PositiveLoginTest and NegativeLoginTest
        Configure timeout for each test method to 30 seconds
        Expected Output: All tests should run in parallel, reducing total execution time.
     */

    @Test
    void positiveLoginTest() {

        CLHomePage homePage = new CLHomePage();
        CLContactListPage contactListPage = new CLContactListPage();
        DriverSingleton.getDriver().get(ConfigReader.getProperty("cl_url"));
        homePage.email.sendKeys(ConfigReader.getProperty("cl_email"));
        homePage.password.sendKeys(ConfigReader.getProperty("cl_password"));
        homePage.submit.click();
        assert contactListPage.logout.isDisplayed();
        DriverSingleton.closeDriver();

    }



}
