package assignemnt.tests.day02;

import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import assignemnt.pages.FluentWebTablePage;
import assignemnt.utilities.Driver;

public class T03_DynamicWebTableManagement {
    /*
     Go to https://claruswaysda.github.io/addRecordWebTable.html
     Add records to the table using DataProvider

    Requirements:
        Create a FluentWebTablePage class
        Implement fluent methods for adding table records
        Use TestNG DataProvider for multiple record sets
        Chain table operations (add, verify, delete if needed)
        Validate table content after each addition
     */

    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        return new Object[][]{
                {"Alice", "25", "USA"},
                {"Bob", "30", "UK"},
                {"Charlie", "28", "Canada"},
                {"Diana", "32", "Australia"},
                {"Ethan", "26", "Germany"}
        };
    }

    @Test(dataProvider = "userData")
    public void testAddRecord(String name, String age, String country) {

        Driver.getDriver().get("https://claruswaysda.github.io/addRecordWebTable.html");

        new FluentWebTablePage()
                .enterName(name)
                .enterAge(age)
                .selectCountry(country)
                .clickAdd()
                .verifyRecordAdded(name);

        System.out.println("Record added & verified: " + name);

    }

    @Test(dependsOnMethods = "testAddRecord")
    public void testDeleteRecord() {

        Driver.getDriver().get("https://claruswaysda.github.io/addRecordWebTable.html");

        new FluentWebTablePage().deleteRecord("Alice");
        System.out.println("Deleted record for Alice");
    }

    @AfterClass
    public void tearDown() {
        Driver.closeDriver();
    }
}