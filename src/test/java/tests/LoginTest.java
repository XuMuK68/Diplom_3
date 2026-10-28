
package tests;

import client.Client;
import client.ClientSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private Client client;

    @Before
    public void setUpClient() {
        client = createTestClient();
    }

    @Test
    @DisplayName("Вход через кнопку «Войти» на главной странице")
    @Description("Проверить, что пользователь может войти через кнопку «Войти» на главной странице и после авторизации видит кнопку заказа.")
    public void loginFromMainPageButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        loginPage(client);

        assertTrue(
                "После входа не открылась главная страница",
                mainPage.isOrderButtonDisplayed()
        );
    }

    @Test
    @DisplayName("Вход через кнопку личного кабинета")
    @Description("Проверить, что пользователь может войти через кнопку личного кабинета и после авторизации видит кнопку заказа.")
    public void loginFromPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickAccountButton();

        loginPage(client);

        assertTrue(
                "После входа не открылась главная страница",
                mainPage.isOrderButtonDisplayed()
        );
    }

    @Test
    @DisplayName("Вход со страницы регистрации")
    @Description("Проверить, что пользователь может перейти со страницы регистрации на страницу входа, авторизоваться и увидеть кнопку заказа.")
    public void loginFromRegisterPage() {
        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.clickLoginButton();
        registerPage.clickRegisterLink();
        registerPage.clickLoginLink();

        loginPage(client);

        assertTrue(
                "После входа не открылась главная страница",
                mainPage.isOrderButtonDisplayed()
        );
    }

    @Test
    @DisplayName("Вход со страницы восстановления пароля")
    @Description("Проверить, что пользователь может перейти со страницы восстановления пароля на страницу входа, авторизоваться и увидеть кнопку заказа.")
    public void loginFromForgotPasswordPage() {
        MainPage mainPage = new MainPage(driver);
        ForgotPasswordPage forgotPasswordPage =
                new ForgotPasswordPage(driver);

        mainPage.clickLoginButton();
        forgotPasswordPage.clickForgotPasswordLink();
        forgotPasswordPage.clickLoginLink();

        loginPage(client);

        assertTrue(
                "После входа не открылась главная страница",
                mainPage.isOrderButtonDisplayed()
        );
    }

    private Client createTestClient() {
        ClientSteps clientSteps = new ClientSteps();
        Client newClient = clientSteps.createTestClient();

        Response response = clientSteps.registerClient(newClient);

        assertTrue(
                "Не удалось создать тестового пользователя через API. Код: "
                        + response.statusCode(),
                response.statusCode() == 200
                        || response.statusCode() == 201
        );

        return newClient;
    }

    private void loginPage(Client client) {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                client.getEmail(),
                client.getPassword()
        );
    }

    private void deleteTestClient(Client client) {
        ClientSteps clientSteps = new ClientSteps();

        try {
            Response response = clientSteps.loginClient(client);

            if (response.statusCode() == 200) {
                String accessToken =
                        response.jsonPath().getString("accessToken");

                if (accessToken != null && !accessToken.isEmpty()) {
                    clientSteps.deleteClient(accessToken);
                }
            }
        } catch (Exception e) {
            System.out.println(
                    "Не удалось удалить тестового пользователя: "
                            + e.getMessage()
            );
        }
    }

    @After
    public void deleteTestClientAfterTest() {
        if (client != null) {
            deleteTestClient(client);
            client = null;
        }
    }
}
