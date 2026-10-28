package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;

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

    @Step("Переход на страницу регистрации")
    public void clickRegisterLink() {
        wait.until(
                ExpectedConditions.elementToBeClickable(registerLink)
        ).click();
    }

    @Step("Переход на страницу входа")
    public void clickLoginLink() {
        wait.until(
                ExpectedConditions.elementToBeClickable(loginLink)
        ).click();
    }

    @Step("Проверка отображения страницы регистрации")
    public boolean isRegisterPageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(nameField)
        ).isDisplayed();
    }

    @Step("Ввод имени")
    public void enterName(String name) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(nameField)
        ).sendKeys(name);
    }

    @Step("Ввод email")
    public void enterEmail(String email) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailField)
        ).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordField)
        ).sendKeys(password);
    }

    @Step("Нажатие кнопки регистрации")
    public void clickRegisterButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(registerButton)
        ).click();
    }

    @Step("Регистрация пользователя")
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    @Step("Проверка отображения ошибки пароля")
    public boolean isPasswordErrorDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordError)
        ).isDisplayed();
    }
}