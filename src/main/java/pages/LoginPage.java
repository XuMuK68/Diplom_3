package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Поле ввода email
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");

    // Поле ввода пароля
    private final By passwordField = By.xpath("//input[@type='password']");

    // Кнопка входа
    private final By loginButton = By.xpath("//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Ввод email")
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField))
                .sendKeys(email);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField))
                .sendKeys(password);
    }

    @Step("Нажатие кнопки входа")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton))
                .click();
    }

    @Step("Полный вход в аккаунт")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    @Step("Проверка отображения страницы входа")
    public boolean isLoginPageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginButton)
        ).isDisplayed();
    }
}