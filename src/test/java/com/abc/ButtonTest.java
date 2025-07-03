package com.abc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ButtonTest extends BaseTest {
    @Test(
            description = "Buttons"
    )
    public void testDeleteBtnClick() {
        Logger logger = LoggerFactory.getLogger(ButtonTest.class);
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        WebElement addBtn = driver.findElement(By.xpath("//button[@onclick='addElement()']"));

        logger.info("Step 1: Add btn is displayed");
        Assert.assertTrue(addBtn.isDisplayed(), "Add btn is hidden");
        addBtn.click();

        logger.info("Step 2: Delete btn is displayed");
        WebElement deleteBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='added-manually']")));
        Assert.assertTrue(deleteBtn.isDisplayed(), "Delete btn is hidden");

        logger.info("Step 3: Delete btn is hidden after click");
        deleteBtn.click();
        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[@class='added-manually']"))));
    }
}
