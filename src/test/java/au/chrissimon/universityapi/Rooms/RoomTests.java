package au.chrissimon.universityapi.Rooms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RoomTests {

    @Autowired
    private RoomApi roomApi;

    @Test
	public void givenIAmAnAdmin_WhenISetupANewRoom() throws Exception {
		ResponseSpec response = roomApi.setupRoom();

        itShouldSetupANewRoom(response);
	}

    private void itShouldSetupANewRoom(ResponseSpec response) {
        response
            .expectStatus()
            .isCreated();
    }
}
