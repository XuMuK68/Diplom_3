package tests;

import org.junit.Test;
import pages.MainPage;

import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseTest {

    @Test
    public void clickBunsTabShowsBunsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.debugTabs();
        mainPage.clickBunButton();

        assertEquals(
                "Булки",
                mainPage.getActiveBunTabText()
        );
    }

    @Test
    public void clickSaucesTabShowsSaucesSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickSauceButton();

        assertEquals(
                "Соусы",
                mainPage.getActiveSauceTabText()
        );
    }

    @Test
    public void clickFillingsTabShowsFillingsSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickFillingButton();

        assertEquals(
                "Начинки",
                mainPage.getActiveFillingTabText()
        );
    }
}