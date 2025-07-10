package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends Page {
    private final By headerBy = By.cssSelector(".spark-heading-1.sds-page-section__title");
    private final By headerLogoBy = By.cssSelector(".header-logo.header-logo__img");
    private final By firstElementInListBy = By.xpath("(//a[contains(@class, 'vehicle-card-link') and contains(@href, '/vehicledetail/')])[1]");

    public String getHeaderText() {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(headerBy));
        return header.getText();
    }

    public void pageIsOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(headerLogoBy));
    }

    public String getFirstElementText() {
        WebElement firstElemet = wait.until(ExpectedConditions.visibilityOfElementLocated(firstElementInListBy));
        return firstElemet.getText();
    }

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }


}
