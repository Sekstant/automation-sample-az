package util;

import com.abc.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static void  deleteFile(String filePath) {
        File path = new File(filePath);
        File[] files = path.listFiles();
        for (File file : files) {
            logger.info("Deleted filename :" + file.getName());
            file.delete();
        }
    }

    public static void generateRandomFile(String filePath, String fileName) {
        File file = new File(filePath + "/" + fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("This is an auto-generated file for upload test.");
            logger.info("Test file created at: " + file.getAbsolutePath());
        } catch (IOException e) {
            logger.error("Failed to create test file", e);
        }
    }
}
