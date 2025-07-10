package com.abc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static util.FileUtil.deleteFile;
import static util.FileUtil.generateRandomFile;

public class UploadDownloadFileTest extends BaseTest {
    Logger logger = LoggerFactory.getLogger(UploadDownloadFileTest.class);
    @Test(
            description = "Upload File"
    )
    public void testUploadDownloadFile() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/upload");
        logger.info("Step 1: Upload File");
        WebElement uploadBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#file-upload")));
        uploadBtn.sendKeys(PATH_TO_UPLOAD  + FILE_NAME);
        WebElement submitBtn = driver.findElement(By.cssSelector("#file-submit"));
        submitBtn.click();

        logger.info("Step 2: Validate Success message");
        By successMessageXPath = By.xpath("//h3[normalize-space()='File Uploaded!']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessageXPath));

        logger.info("Step 3: Download file");
        driver.get("https://the-internet.herokuapp.com/download");
        var fileNameXPath = "//a[normalize-space()='%s']".formatted(FILE_NAME);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(fileNameXPath))).click();
        Thread.sleep(10000);
        Assert.assertTrue(new File(PATH_TO_DOWNLOAD + FILE_NAME).exists(), "File is not downloaded");

    }

    @BeforeClass
    public void generateFile() {
        generateRandomFile(PATH_TO_UPLOAD, FILE_NAME);
    }

    @AfterClass
    public void deleteDownloadedFilesAfterTest() {
        deleteFile(PATH_TO_DOWNLOAD);

    }

    @AfterClass
    public void deleteUploadedFilesAfterTest() {
        deleteFile(PATH_TO_UPLOAD);
    }
}
