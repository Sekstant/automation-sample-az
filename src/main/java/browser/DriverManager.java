package browser;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import util.ConfigReader;

import java.util.HashMap;

public class DriverManager {
    public static WebDriver getDriver( String browser) {
        final String PATH_TO_DOWNLOAD = ConfigReader.getProperty("pathToDownload");
        switch (browser) {
            case "chrome":
                var chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.password_manager_leak_detection", false);
                chromePrefs.put("download.default_directory", PATH_TO_DOWNLOAD);
                chromePrefs.put("savefile.default_directory", PATH_TO_DOWNLOAD);
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("prefs", chromePrefs);
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
                return new ChromeDriver(chromeOptions);

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                return new FirefoxDriver(firefoxOptions);

            default:
                throw new RuntimeException("Unknown browser  type: " + browser);
        }
    }
}
