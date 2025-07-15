package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ConfigReader;

import java.util.List;

public class SearchResultPage extends Page {
    private final By headerBy = By.cssSelector(".spark-heading-1.sds-page-section__title");
    private final By headerLogoBy = By.cssSelector(".header-logo.header-logo__img");
    private final By listOfFoundElementsBy = By.xpath("(//a[contains(@class, 'vehicle-card-link')])");
    private final String resultUrl = ConfigReader.getProperty("resultUrl");

    public String getHeaderText() {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(headerBy));
        return header.getText();
    }

    public void waitUntilPageIsOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(headerLogoBy));
    }

    private List<WebElement> getAllResultElements() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(listOfFoundElementsBy));

    }

    public String getFirstElementText() {
        return getAllResultElements().get(0).getText();
    }

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public void goToURL() {
        driver.get(resultUrl);
    }
}
