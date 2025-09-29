package tasks.tests.day01;

import assignemnt.utilities.Driver;
import org.testng.annotations.Test;
import tasks.pages.ClaruswaySdaPage;

public class T01_WebTableRecordManagement {
    /*
    Go to https://claruswaysda.github.io/addRecordWebTable.html
    Add 5 records
    Delete first record you created.
    (Use Fluent POM and don't use any index in locators)
*/



    @Test
    public void test01() {
        Driver.getDriver().get("https://claruswaysda.github.io");
        ClaruswaySdaPage sdaPage = new ClaruswaySdaPage();
        sdaPage
                .clickWebTable()
                .enterFakeRecord(5)
                .deleteLastAddedRecord();
    }

}
