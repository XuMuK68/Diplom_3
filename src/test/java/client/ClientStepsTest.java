package client;

import io.restassured.response.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;

public class ClientStepsTest {

    @Test
    public void registerAndDeleteTestClient() {
        ClientSteps clientSteps = new ClientSteps();
        Client client = clientSteps.createTestClient();

        Response registerResponse = clientSteps.registerClient(client);

        assertEquals(200, registerResponse.statusCode());

        String accessToken =
                registerResponse.jsonPath().getString("accessToken");
        String refreshToken =
                registerResponse.jsonPath().getString("refreshToken");

        assertNotNull(accessToken);
        assertFalse(accessToken.isEmpty());
        assertNotNull(refreshToken);
        assertFalse(refreshToken.isEmpty());

        Response deleteResponse = clientSteps.deleteClient(accessToken);

        assertEquals(202, deleteResponse.statusCode());
    }

    @Test
    public void loginClientReturnsAccessToken() {
        ClientSteps clientSteps = new ClientSteps();
        Client client = clientSteps.createTestClient();

        Response registerResponse = clientSteps.registerClient(client);
        assertEquals(200, registerResponse.statusCode());

        Response loginResponse = clientSteps.loginClient(client);

        assertEquals(200, loginResponse.statusCode());

        String accessToken =
                loginResponse.jsonPath().getString("accessToken");

        assertNotNull(accessToken);
        assertFalse(accessToken.isEmpty());

        Response deleteResponse = clientSteps.deleteClient(accessToken);
        assertEquals(202, deleteResponse.statusCode());
    }
}