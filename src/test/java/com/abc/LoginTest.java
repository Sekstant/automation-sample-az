package com.abc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginTest extends BaseTest {

    @Test(
            description = "Login"
    )
    public void testLogin() {
        Logger logger = LoggerFactory.getLogger(LoginTest.class);
        driver.get("https://the-internet.herokuapp.com/login");

        String usernameToEnter = "tomsmith";
        String passwordToEnter = "SuperSecretPassword!";

        WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"login\"]/button"));

        logger.info("Step 1: Provide username and password and click on Login btn");
        wait.until(ExpectedConditions.visibilityOf(username));
        wait.until(ExpectedConditions.visibilityOf(password));
        username.sendKeys(usernameToEnter);
        password.sendKeys(passwordToEnter);
        loginBtn.click();

        logger.info("Step 2: Validate Success message");
        WebElement successAlert = driver.findElement(By.cssSelector(".subheader"));
        Assert.assertEquals(successAlert.getText(), "Welcome to the Secure Area. When you are done click logout below.", "There is invalid/no success alert ");
    }
}
