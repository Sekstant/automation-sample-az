package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ConfigReader;

public class HomePage extends Page {
    @FindBy(xpath="//input[@id='carson-one-hitter']")
    private WebElement searchInput;
    @FindBy(className = "carson-submit-button")
    private WebElement searchBtn;
    private final String homeUrl = ConfigReader.getProperty("homeUrl");

    public void provideSearchString(String model) {
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        searchInput.sendKeys(model);
    }

    public void clickOnSearchIcon() {
        wait.until(ExpectedConditions.visibilityOf(searchBtn));
        searchBtn.click();
    }

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goToURL(){
        driver.get(homeUrl);
    }
}
