package tests;

import client.Client;
import client.ClientSteps;
import io.restassured.response.Response;
import org.junit.Test;
import pages.MainPage;
import pages.RegisterPage;
import pages.LoginPage;

import java.util.UUID;

import static org.junit.Assert.assertTrue;

public class RegisterTest extends BaseTest {

    @Test
    public void successfulRegistrationTest() {
        ClientSteps clientSteps = new ClientSteps();
        Client client = clientSteps.createTestClient();

        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        try {
            mainPage.clickLoginButton();
            registerPage.clickRegisterLink();

            registerPage.register(
                    client.getName(),
                    client.getEmail(),
                    client.getPassword()
            );

            assertTrue(
                    "Не открылась страница входа после регистрации",
                    loginPage.isLoginPageDisplayed()
            );
        } finally {
            deleteTestClientIfExists(clientSteps, client);
        }
    }

    @Test
    public void registrationWithShortPasswordShowsError() {
        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        String email = "test_" + UUID.randomUUID() + "@ya.ru";

        mainPage.clickLoginButton();
        registerPage.clickRegisterLink();

        registerPage.register(
                "Александр Гаврилов",
                email,
                "12345"
        );

        assertTrue(
                "Не появилось сообщение о некорректном пароле",
                registerPage.isPasswordErrorDisplayed()
        );
    }

    private void deleteTestClientIfExists(
            ClientSteps clientSteps,
            Client client
    ) {
        try {
            Response loginResponse = clientSteps.loginClient(client);

            if (loginResponse.statusCode() == 200) {
                String accessToken =
                        loginResponse.jsonPath().getString("accessToken");

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