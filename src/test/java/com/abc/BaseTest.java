package com.abc;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseTest {
    protected WebDriver driver;
    protected Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected WebDriverWait wait;

    protected final String PATH_TO_DOWNLOAD = "C:\\Users\\azanko\\IdeaProjects\\automation-sample\\src\\test\\resources\\downloadedFiles";
    protected final String PATH_TO_UPLOAD = "C:\\Users\\azanko\\IdeaProjects\\automation-sample\\src\\test\\resources\\uploadedFiles";
    protected final String FILE_NAME = RandomStringUtils.randomAlphanumeric(17) + ".txt";

    @BeforeMethod
    public void precondition() {

        driver = DriverManager.getDriver();
        logger.info("Driver is started");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().fullscreen();
    }

    @AfterMethod
    public void postcondition() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("Driver is closed");
        }
    }
}
