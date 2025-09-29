package tasks.tests.day02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tasks.utilities.Driver;

import java.time.Duration;

public class T02DataProviderParallelExecution {

    /*Task 2: Data Provider Parallel Execution

        Objective: Implement a data-driven test that runs search functionality with multiple search terms in
        parallel.

        Requirements:
        Create a data provider with at least 5 different search terms
        Enable parallel execution for the data provider
        Create a test method that accepts search terms and validates results
        Configure XML file with data-provider-thread-count of 2
        Expected Output: Each search term should be tested simultaneously in separate threads.
     */

    @DataProvider(name = "searchTerms", parallel = true)
    public Object[][] getSearchTerms() {
        return new Object[][]{
                {"Selenium"},
                {"TestNG"},
                {"Parallel Execution"},
                {"Java Faker"},
                {"Page Object Model"}
        };
    }

    @Test(dataProvider = "searchTerms")
    public void searchTest(String term) {

        Driver.getDriver().get("https://www.amazon.com");

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox"))
        );

        searchBox.sendKeys(term);
        searchBox.submit();

        // Wait until the title contains the search term
        boolean titleUpdated = wait.until(ExpectedConditions.titleContains(term));

        Assert.assertTrue(titleUpdated,
                "Page title did not contain search term. Title was: " + Driver.getDriver().getTitle());

        System.out.println("Search completed for: " + term +
                " | Thread ID: " + Thread.currentThread().getId());
    }

    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();
    }
}
