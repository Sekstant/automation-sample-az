package com.abc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.SearchResultPage;

public class CarsTest extends BaseTest {
    Logger logger = LoggerFactory.getLogger(CarsTest.class);

    @Test(
            description = "Cars"
    )
    public void testCars() {
        HomePage homePage = new HomePage(driver);
        homePage.goToURL("https://www.cars.com/");

        logger.info("Step 1: Provide Make and Model");
        String searchParametr = "Ford";
        homePage.provideSearchString(searchParametr);

        logger.info("Step 2: Trigger search");
        homePage.clickOnSearchIcon();

        logger.info("Step 3: Wait till page is opened");
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        searchResultPage.waitUntilPageIsOpened();

        logger.info("Step 4: Check that first element in result contains data from Search filed");
        String textFirstElement = searchResultPage.getFirstElementText();
        logger.info(textFirstElement);
        Assert.assertTrue(textFirstElement.contains(searchParametr), "First result doesn’t contain the search term: " + searchParametr);
    }
}
