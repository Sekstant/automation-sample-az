package com.abc;

import com.testdata.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class InputTest extends BaseTest {
    Logger logger = LoggerFactory.getLogger(InputTest.class);
    @Test(
            description = "Input",
            dataProvider = "provideInputValues",
            dataProviderClass = DataProviders.class
    )

    public void testInput(String valueToInsertString) {

        driver.get("https://the-internet.herokuapp.com/inputs");

        logger.info("Step 1:Input is displayed");
        WebElement input =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='number']")));
        wait.until(ExpectedConditions.visibilityOf(input));
        Assert.assertTrue(input.isDisplayed(), "There  is no input on page");

        logger.info("Step 2:Put a random number");
        input.sendKeys(valueToInsertString);
        Assert.assertEquals(input.getAttribute("value"), valueToInsertString, "Invalid number");
    }

}
