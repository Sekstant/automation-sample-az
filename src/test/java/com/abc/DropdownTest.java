package com.abc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DropdownTest extends BaseTest {
    Logger logger = LoggerFactory.getLogger(DropdownTest.class);
    @Test(
            description = "Dropdown"
    )
    public void testSelectDropdown() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        By dropdownLocator = By.id("dropdown");

        logger.info("Step 1:Dropdown is displayed");
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownLocator));
        Assert.assertTrue(dropdownElement.isDisplayed(), "Dropdown is not displayed");

        logger.info("Step 2: Select Option 2");
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText("Option 2");

        logger.info("Step 3: Verify that Option 2 is selected");
        WebElement selectedOption = dropdown.getFirstSelectedOption();
        Assert.assertEquals(selectedOption.getText(), "Option 2", "Invalid Option is selected ( Option 1 instead option 2");
    }
}
