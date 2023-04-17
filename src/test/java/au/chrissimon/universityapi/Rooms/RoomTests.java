package au.chrissimon.universityapi.Rooms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import au.chrissimon.universityapi.Helpers;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RoomTests {

    @Value(value="${local.server.port}")
    private int port;

    @Test
	public void givenIAmAnAdmin_WhenISetupANewRoom() throws Exception {
		ResponseSpec response = Helpers.newWebClient(port)
            .post()
                .uri("/rooms")
                .exchange();

        itShouldSetupANewRoom(response);
	}

    private void itShouldSetupANewRoom(ResponseSpec response) {
        response
            .expectStatus()
            .isCreated();
    }
}
