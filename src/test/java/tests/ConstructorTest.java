package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.MainPage;

import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переключение на вкладку «Булки»")
    @Description("Проверить, что при нажатии на вкладку «Булки» она становится активной.")
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
    @DisplayName("Переключение на вкладку «Соусы»")
    @Description("Проверить, что при нажатии на вкладку «Соусы» она становится активной.")
    public void clickSaucesTabShowsSaucesSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickSauceButton();

        assertEquals(
                "Соусы",
                mainPage.getActiveSauceTabText()
        );
    }

    @Test
    @DisplayName("Переключение на вкладку «Начинки»")
    @Description("Проверить, что при нажатии на вкладку «Начинки» она становится активной.")
    public void clickFillingsTabShowsFillingsSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickFillingButton();

        assertEquals(
                "Начинки",
                mainPage.getActiveFillingTabText()
        );
    }
}