package com.abc;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ConfigReader;

public abstract class BaseTest {
    protected WebDriver driver;
    protected Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected WebDriverWait wait;

    protected final String PATH_TO_DOWNLOAD = ConfigReader.getProperty("pathToDownload");
    protected final String PATH_TO_UPLOAD = ConfigReader.getProperty("pathToUpload");
    protected final String FILE_NAME = RandomStringUtils.randomAlphanumeric(17) + ".txt";

    @BeforeMethod
    public void precondition() {

        String browser = ConfigReader.getProperty("browser");
        driver = DriverManager.getDriver(browser);
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
