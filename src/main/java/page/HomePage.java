package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ConfigReader;

public class HomePage extends Page {
    private final By searchBy = By.xpath("//input[@id='carson-one-hitter']");
    private final By searchBtnBy = By.className("carson-submit-button");
    private final String homeUrl = ConfigReader.getProperty("homeUrl");

    public void provideSearchString(String model) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBy));
        searchInput.sendKeys(model);
    }

    public void clickOnSearchIcon() {
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchBtnBy));
        searchBtn.click();
    }

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goToURL(){
        driver.get(homeUrl);
    }
}
