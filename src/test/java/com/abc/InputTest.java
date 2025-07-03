package com.abc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class InputTest extends BaseTest {

    @Test(
            description = "Input"
    )

    public void testInput() {
        Logger logger = LoggerFactory.getLogger(InputTest.class);
        driver.get("https://the-internet.herokuapp.com/inputs");

        logger.info("Step 1:Input is displayed");
        WebElement input = driver.findElement(By.xpath("//input[@type='number']"));
        wait.until(ExpectedConditions.visibilityOf(input));
        Assert.assertTrue(input.isDisplayed(), "There  is no input on page");

        logger.info("Step 2:Put a random number");
        int valueToInsertInt = ThreadLocalRandom.current().nextInt(1000, 100000);
        String valueToInsertString = String.valueOf(valueToInsertInt);
        input.sendKeys(valueToInsertString);
        Assert.assertEquals(input.getAttribute("value"), valueToInsertString, "Invalid number");
    }

}
