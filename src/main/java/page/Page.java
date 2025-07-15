package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ConfigReader;

public abstract class Page {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        int duration =  Integer. parseInt(ConfigReader.getProperty("durationOfSecond"));
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(duration));
        PageFactory.initElements(driver, this);
    }
    abstract void goToURL();
}
