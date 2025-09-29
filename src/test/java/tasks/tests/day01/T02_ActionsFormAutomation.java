package tasks.tests.day01;

import assignemnt.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;
import tasks.pages.ActionsFormPage;

public class T02_ActionsFormAutomation {

    /*
    Go to https://claruswaysda.github.io/ActionsForm.html
    Fill form and submit
    Do all actions and assert
    */

    @Test
    public void testAddRecord() {


        Driver.getDriver().get("https://claruswaysda.github.io/ActionsForm.html");
        ActionsFormPage fillFormPage = new ActionsFormPage();

        String alertText=
                fillFormPage
                        .fillName("Test")
                        .fillAge("25")
                        .fillOptions("it")
                        .fillInterest()
                        .fillGender()
                        .formSubmit()
                        .getAlertMessage();

        Assert.assertTrue(alertText.contains("Your passcode is:"));


        Driver.closeDriver();
    }
    @Test
    public void testAddRecord02() {


        Driver.getDriver().get("https://claruswaysda.github.io/ActionsForm.html");
        ActionsFormPage fillFormPage = new ActionsFormPage();

        fillFormPage
                .fillName("Test")
                .fillAge("25")
                .fillOptions("it")
                .fillInterest()
                .fillGender()
                .formSubmit()
                .verifyPasscode();

        Driver.closeDriver();
    }

}