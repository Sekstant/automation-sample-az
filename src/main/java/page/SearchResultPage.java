package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ConfigReader;

import java.util.List;

public class SearchResultPage extends Page {
    @FindBy(css = ".spark-heading-1.sds-page-section__title")
    private WebElement header;
    @FindBy(css = ".header-logo.header-logo__img")
    private WebElement headerLogo;
    @FindBy(xpath = "(//a[contains(@class, 'vehicle-card-link')])")
    private WebElement listOfFoundElements;
    private final String resultUrl = ConfigReader.getProperty("resultUrl");

    public String getHeaderText() {
        wait.until(ExpectedConditions.visibilityOf(header));
        return header.getText();
    }

    public void waitUntilPageIsOpened() {
        wait.until(ExpectedConditions.visibilityOf(headerLogo));
    }

    private List<WebElement> getAllResultElements() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(listOfFoundElements));
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
