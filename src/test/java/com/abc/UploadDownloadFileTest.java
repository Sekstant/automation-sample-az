package com.abc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadDownloadFileTest extends BaseTest {

    @Test(
            description = "Upload File"
    )
    public void testUploadDownloadFile() throws InterruptedException {
        Logger logger = LoggerFactory.getLogger(UploadDownloadFileTest.class);
        driver.get("https://the-internet.herokuapp.com/upload");
        logger.info("Step 1: Upload File");
        WebElement uploadBtn = driver.findElement(By.cssSelector("#file-upload"));
        uploadBtn.sendKeys(PATH_TO_UPLOAD + "/" + FILE_NAME);

        WebElement submitBtn = driver.findElement(By.cssSelector("#file-submit"));
        submitBtn.click();
        Thread.sleep(10000);

        logger.info("Step 2: Validate Success message");
        Assert.assertEquals(driver.findElement(By.xpath("//h3[normalize-space()='File Uploaded!']")).getText(), "File Uploaded!", "File is not uploaded");

        logger.info("Step 3: Download file");
        driver.get("https://the-internet.herokuapp.com/download");
        String fileNameByXPath = "//a[normalize-space()='" + FILE_NAME + "']";
        driver.findElement(By.xpath(fileNameByXPath)).click();
        Thread.sleep(10000);

    }

    @BeforeClass
    public void generateRandomFile() {
        File file = new File(PATH_TO_UPLOAD + "/" + FILE_NAME);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("This is an auto-generated file for upload test.");
            logger.info("Test file created at: " + file.getAbsolutePath());
        } catch (IOException e) {
            logger.error("Failed to create test file", e);
            throw new RuntimeException(e);
        }
    }

    @AfterTest
    public void deleteDownloadedFilesAfterTest() {
        File path = new File(PATH_TO_DOWNLOAD);
        File[] files = path.listFiles();
        for (File file : files) {
            logger.info("Deleted filename :" + file.getName());
            file.delete();
        }
    }

    @AfterTest
    public void deleteUploadedFilesAfterTest() {
        File path = new File(PATH_TO_UPLOAD);
        File[] files = path.listFiles();
        for (File file : files) {
            logger.info("Deleted filename :" + file.getName());
            file.delete();
        }
    }


}
