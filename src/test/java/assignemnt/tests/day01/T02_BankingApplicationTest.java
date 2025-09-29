package assignemnt.tests.day01;

import assignemnt.pages.*;
import assignemnt.utilities.ConfigReader;
import assignemnt.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class T02_BankingApplicationTest {

    /*
    HomeWork For Weekend :2Example 2: Banking Application Test
    Target: https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login

    Test Scenario:
        Open 5 new customer accounts ✅
        Deposit 100 USD to each account ✅
        Withdraw 100 USD from any one account ✅
        Delete all created accounts ✅
        Verify account operations ✅

    Page Objects Needed:
        ManagerLoginPage (manager interface elements) ✅
        CustomerManagementPage (add/delete customer elements) ✅
        AccountManagementPage (account creation elements)✅
        CustomerLoginPage (customer interface elements) ✅
        TransactionPage (deposit/withdraw elements) ✅

    Assertions:
        Verify account creation success ✅
        Verify deposit transactions ✅
        Verify withdrawal transactions ✅
        Verify account balance updates ✅
        Verify account deletion ✅
     */

    @Test
    void bankApplicationTest() throws InterruptedException {

        //Page object creation
        AccountManagementPage accountManagementPage = new AccountManagementPage();
        CustomerLoginPage customerLoginPage = new CustomerLoginPage();
        ManagerLoginPage managerLoginPage = new ManagerLoginPage();
        CustomerManagementPage customerManagementPage = new CustomerManagementPage();
        TransactionPage transactionPage = new TransactionPage();


        //Navigate to the bank application
        Driver.getDriver().get(ConfigReader.getProperty("bank_url"));

        //open 5 new customer accounts
        accountManagementPage.managerLoginBtn.click();


        // loop to add new customers and open their accounts
        for (int i =1 ; i<6 ; i++) {

            //add customer
            managerLoginPage.addCustomerBtn.click();
            customerManagementPage.fName.sendKeys(ConfigReader.getProperty("user"+i+"_fName"));
            customerManagementPage.lName.sendKeys(ConfigReader.getProperty("user"+i+"_lName"));
            customerManagementPage.postCode.sendKeys(ConfigReader.getProperty("user"+i+"_code"));
            customerManagementPage.addBtn.click();

            Assert.assertTrue(Driver.getDriver().switchTo().alert().getText().contains("Customer added successfully"),"Customer wasn't added successfully");
            Driver.getDriver().switchTo().alert().accept();

            //open account
            managerLoginPage.openAccountBtn.click();
            new Select(customerManagementPage.userSelect).selectByVisibleText(ConfigReader.getProperty("user"+i+"_fName")+" "+ConfigReader.getProperty("user"+i+"_lName"));
            new Select(customerManagementPage.userCurrency).selectByValue("Dollar");
            customerManagementPage.processBtn.click();

            Assert.assertTrue(Driver.getDriver().switchTo().alert().getText().contains("Account created successfully"),"account wasn't created successfully");
            Driver.getDriver().switchTo().alert().accept();


        }

        // loop to login into customer accounts and deposit and withdraw
        for (int i = 1 ; i < 6; i++){

            //Customer login
            accountManagementPage.homeBtn.click();

            accountManagementPage.customerLoginBtn.click();
            customerLoginPage.login(ConfigReader.getProperty("user"+i+"_fName")+" "+ConfigReader.getProperty("user"+i+"_lName"));

            //deposit 100 USD
            transactionPage.depositBtn.click();
            transactionPage.amountInput.sendKeys("100");
            transactionPage.depositSubmitBtn.click();
            Assert.assertEquals(transactionPage.balanceLabel.getText(), "100", "Balance updated successfully");
            Assert.assertEquals(transactionPage.message.getText(), "Deposit Successful", "Deposit wan not successful");

            //logout
            transactionPage.logoutBtn.click();
        }

        //Customer login on any account to withdrawal 100 USD
        accountManagementPage.homeBtn.click();

        accountManagementPage.customerLoginBtn.click();
        customerLoginPage.login(ConfigReader.getProperty("user1_fName")+" "+ConfigReader.getProperty("user1_lName"));

        //withdrawal 100 USD
        transactionPage.withdrawlBtn.click();
        transactionPage.amountInput.sendKeys("100");
        transactionPage.withdrawSubmitBtn.click();
        Assert.assertEquals(transactionPage.balanceLabel.getText(), "0", "Balance updated successfully");
        Assert.assertEquals(transactionPage.message.getText(), "Transaction successful", "withdrawal wan not successful");

        //logout
        transactionPage.logoutBtn.click();

        //Delete all accounts created
        accountManagementPage.homeBtn.click();
        accountManagementPage.managerLoginBtn.click();
        customerManagementPage.customersBtn.click();

        //loop to delete the created accounts
        for (int i =1 ; i<6 ; i++) {
            customerManagementPage.searchBox.clear();
            customerManagementPage.searchBox.sendKeys(ConfigReader.getProperty("user"+i+"_code"));
            customerManagementPage.deleteBtn.click();

            Thread.sleep(500);

            // to check that no rows are displayed
            List<WebElement> rows = Driver.getDriver().findElements(By.xpath("//table//tbody//tr"));

            Assert.assertTrue(rows.isEmpty(), "Customer with code "
                    + ConfigReader.getProperty("user"+i+"_code") + " was not deleted!");
        }


        Driver.closeDriver();
    }
}
