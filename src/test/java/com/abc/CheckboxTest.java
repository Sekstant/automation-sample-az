package com.abc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CheckboxTest extends BaseTest {

    @Test(
            description = "Checkboxes"
    )
    public void testSelectDeselectCheckbox() {
        Logger logger = LoggerFactory.getLogger(CheckboxTest.class);
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        WebElement checkbox1 = driver.findElement(By.xpath("//input[1]"));
        WebElement checkbox2 = driver.findElement(By.xpath("//input[2]"));

        logger.info("Step 1: Chekbox 1 is deselected ");
        wait.until(ExpectedConditions.visibilityOf(checkbox1));
        Assert.assertFalse(checkbox1.isSelected(), "Checkbox 1 is selected");

        logger.info("Step 2: Checkbox 2 is selected ");
        wait.until(ExpectedConditions.visibilityOf(checkbox2));
        Assert.assertTrue(checkbox2.isSelected(), "Checkbox 2 is Unselected");

        logger.info("Step 3: Chekbox 1 is selected after click ");
        checkbox1.click();
        Assert.assertTrue(checkbox1.isSelected(), "Checkbox 1 is deselected");

        logger.info("Step 4: Checkbox 2 is deselected after click");
        checkbox2.click();
        Assert.assertFalse(checkbox2.isSelected(), "Checkbox  2 is selected");
    }
}
