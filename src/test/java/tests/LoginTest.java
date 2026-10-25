package tests;

import client.Client;
import client.ClientSteps;
import io.restassured.response.Response;
import org.junit.Test;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void loginFromMainPageButton() {
        Client client = createTestClient();

        try {
            MainPage mainPage = new MainPage(driver);
            mainPage.clickLoginButton();

            loginPage(client);

            assertTrue(
                    "После входа не открылась главная страница",
                    mainPage.isOrderButtonDisplayed()
            );
        } finally {
            deleteTestClient(client);
        }
    }

    @Test
    public void loginFromPersonalAccountButton() {
        Client client = createTestClient();

        try {
            MainPage mainPage = new MainPage(driver);
            mainPage.clickAccountButton();

            loginPage(client);

            assertTrue(
                    "После входа не открылась главная страница",
                    mainPage.isOrderButtonDisplayed()
            );
        } finally {
            deleteTestClient(client);
        }
    }

    @Test
    public void loginFromRegisterPage() {
        Client client = createTestClient();

        try {
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
        } finally {
            deleteTestClient(client);
        }
    }

    @Test
    public void loginFromForgotPasswordPage() {
        Client client = createTestClient();

        try {
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
        } finally {
            deleteTestClient(client);
        }
    }

    private Client createTestClient() {
        ClientSteps clientSteps = new ClientSteps();
        Client client = clientSteps.createTestClient();

        Response response = clientSteps.registerClient(client);

        assertTrue(
                "Не удалось создать тестового пользователя через API. Код: "
                        + response.statusCode(),
                response.statusCode() == 200
                        || response.statusCode() == 201
        );

        return client;
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
}