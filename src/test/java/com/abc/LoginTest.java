package com.abc;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.qameta.allure.Allure;

public class LoginTest extends BaseTest {
    Logger logger = LoggerFactory.getLogger(LoginTest.class);
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton   = By.cssSelector("#login button");
    private final By successAlert  = By.cssSelector(".flash.success");
    @Test(
            description = "Login"
    )
    public void testLogin() {

        driver.get("https://the-internet.herokuapp.com/login");

        String usernameToEnter = "tomsmith";
        String passwordToEnter = "SuperSecretPassword!";

        Allure.step("Step 1: Provide username and password and click on Login btn");
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        username.sendKeys(usernameToEnter);
        password.sendKeys(passwordToEnter);
        loginBtn.click();

        Allure.step("Step 2: Validate Success message");
        WebElement successAlert = driver.findElement(By.cssSelector(".subheader"));
        Assert.assertEquals(successAlert.getText(), "Welcome to the Secure Area. When you are done click logout below.", "There is invalid/no success alert ");
    }
}
