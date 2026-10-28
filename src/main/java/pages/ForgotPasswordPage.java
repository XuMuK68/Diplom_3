
package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By forgotPasswordLink =
            By.xpath("//a[contains(., 'Восстановить пароль')]");

    private final By loginLink =
            By.xpath("//a[normalize-space()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Нажать на ссылку «Восстановить пароль»")
    public void clickForgotPasswordLink() {
        wait.until(
                ExpectedConditions.elementToBeClickable(forgotPasswordLink)
        ).click();
    }

    @Step("Нажать на ссылку «Войти»")
    public void clickLoginLink() {
        wait.until(
                ExpectedConditions.elementToBeClickable(loginLink)
        ).click();
    }

    @Step("Проверить отображение страницы восстановления пароля")
    public boolean isForgotPasswordPageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginLink)
        ).isDisplayed();
    }
}
