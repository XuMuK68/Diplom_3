package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Существующие локаторы
    private final By registerLink =
            By.xpath("//a[contains(., 'Зарегистрироваться')]");

    private final By loginLink =
            By.xpath("//a[normalize-space()='Войти']");

    // Поля формы регистрации
    private final By nameField =
            By.xpath("//label[text()='Имя']/following-sibling::input");

    private final By emailField =
            By.xpath("//label[text()='Email']/following-sibling::input");

    private final By passwordField =
            By.xpath("//input[@type='password']");

    private final By registerButton =
            By.xpath("//button[normalize-space()='Зарегистрироваться']");

    private final By passwordError =
            By.xpath("//*[contains(text(), 'Некорректный пароль')]");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Переходы между страницами
    public void clickRegisterLink() {
        wait.until(
                ExpectedConditions.elementToBeClickable(registerLink)
        ).click();
    }

    public void clickLoginLink() {
        wait.until(
                ExpectedConditions.elementToBeClickable(loginLink)
        ).click();
    }

    // Проверка страницы регистрации
    public boolean isRegisterPageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(nameField)
        ).isDisplayed();
    }

    // Заполнение полей
    public void enterName(String name) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(nameField)
        ).sendKeys(name);
    }

    public void enterEmail(String email) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailField)
        ).sendKeys(email);
    }

    public void enterPassword(String password) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordField)
        ).sendKeys(password);
    }

    public void clickRegisterButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(registerButton)
        ).click();
    }

    // Полная регистрация
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    // Проверка ошибки короткого пароля
    public boolean isPasswordErrorDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordError)
        ).isDisplayed();
    }
}