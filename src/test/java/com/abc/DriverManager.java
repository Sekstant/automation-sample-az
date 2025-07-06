package com.abc;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import util.ConfigReader;

import java.util.HashMap;

public class DriverManager {
    private static WebDriver driver;


    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigReader.getProperty("browser");
            final String PATH_TO_DOWNLOAD = "C:\\Users\\azanko\\IdeaProjects\\automation-sample\\src\\test\\resources\\downloadedFiles";
            switch (browser) {
                case "chrome":
                    var chromePrefs = new HashMap<String, Object>();
                    chromePrefs.put("profile.password_manager_leak_detection", false);
                    chromePrefs.put("download.default_directory", PATH_TO_DOWNLOAD);
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setExperimentalOption("prefs", chromePrefs);
                    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                default:
                    throw new RuntimeException("Unknown browser  type: " + browser);
            }
        }
        return driver;
    }


}
