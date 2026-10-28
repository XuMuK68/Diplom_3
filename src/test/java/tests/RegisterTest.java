package tests;

import client.Client;
import client.ClientSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import pages.MainPage;
import pages.RegisterPage;
import pages.LoginPage;

import java.util.UUID;

import static org.junit.Assert.assertTrue;

public class RegisterTest extends BaseTest {

    private ClientSteps clientSteps;
    private Client client;

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверить, что после регистрации пользователя открывается страница входа.")
    public void successfulRegistrationTest() {
        clientSteps = new ClientSteps();
        client = clientSteps.createTestClient();

        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);

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
    }

    @Test
    @DisplayName("Ошибка при регистрации с коротким паролем")
    @Description("Проверить, что при вводе пароля короче допустимого отображается сообщение об ошибке.")
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

    @After
    public void deleteTestClientAfterTest() {
        if (clientSteps != null && client != null) {
            deleteTestClientIfExists(clientSteps, client);
            client = null;
        }
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