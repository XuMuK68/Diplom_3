package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginButton =
            By.xpath("//button[contains(., 'Войти в аккаунт')]");

    private final By profileButton =
            By.xpath("//a[.//p[normalize-space()='Личный Кабинет']]");

    private final By bunButton =
            By.xpath("//span[normalize-space()='Булки']");

    private final By sauceButton =
            By.xpath("//span[normalize-space()='Соусы']");

    private final By fillingButton =
            By.xpath("//span[normalize-space()='Начинки']");

    private final By activeBunTab =
            By.xpath("//div[contains(@class,'tab_tab_type_current')]" +
                    "[.//span[normalize-space()='Булки']]");

    private final By activeSauceTab =
            By.xpath("//div[contains(@class,'tab_tab_type_current')]" +
                    "[.//span[normalize-space()='Соусы']]");

    private final By activeFillingTab =
            By.xpath("//div[contains(@class,'tab_tab_type_current')]" +
                    "[.//span[normalize-space()='Начинки']]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void clickAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(profileButton)).click();
    }

    public void clickBunButton() {
        WebElement tab = wait.until(
                ExpectedConditions.visibilityOfElementLocated(bunButton)
        );

        new Actions(driver)
                .moveToElement(tab)
                .click()
                .perform();
    }

    public void clickSauceButton() {
        wait.until(ExpectedConditions.elementToBeClickable(sauceButton)).click();
    }

    public void clickFillingButton() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingButton)).click();
    }

    public String getActiveBunTabText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(activeBunTab)
        ).getText();
    }

    public String getActiveSauceTabText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(activeSauceTab)
        ).getText();
    }

    public String getActiveFillingTabText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(activeFillingTab)
        ).getText();
    }

    private final By orderButton =
            By.xpath("//button[normalize-space()='Оформить заказ']");

    public boolean isOrderButtonDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(orderButton)
        ).isDisplayed();
    }

    public void debugTabs() {
        System.out.println("Булки: " +
                driver.findElements(By.xpath("//span[normalize-space()='Булки']")).size());

        System.out.println("Соусы: " +
                driver.findElements(By.xpath("//span[normalize-space()='Соусы']")).size());

        System.out.println("Начинки: " +
                driver.findElements(By.xpath("//span[normalize-space()='Начинки']")).size());

        System.out.println("Контейнеры вкладок: " +
                driver.findElements(By.xpath("//div[contains(@class,'tab_tab')]")).size());
    }
}