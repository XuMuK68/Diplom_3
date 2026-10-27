package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class ClientSteps {

    private static final String BASE_URL =
            "https://qa-stellarburgers.education-services.ru";

    // Создание уникальных данных тестового пользователя
    @Step("Создать тестового пользователя")
    public Client createTestClient() {
        String email = "test_" + UUID.randomUUID() + "@ya.ru";
        String password = "123456123";
        String name = "Александр Гаврилов";

        return new Client(email, password, name);
    }

    // Регистрация пользователя через API
    @Step("Зарегистрировать пользователя через API")
    public Response registerClient(Client client) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(client)
                .when()
                .post("/api/auth/register");
    }

    // Удаление пользователя через API
    @Step("Удалить пользователя через API")
    public Response deleteClient(String accessToken) {
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user");
    }

    @Step("Авторизовать пользователя через API")
    public Response loginClient(Client client) {
        LoginClient loginClient = new LoginClient(
                client.getEmail(),
                client.getPassword()
        );

        return given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(loginClient)
                .when()
                .post("/api/auth/login");
    }
}