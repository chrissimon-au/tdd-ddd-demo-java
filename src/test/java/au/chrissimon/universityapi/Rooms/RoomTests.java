package au.chrissimon.universityapi.Rooms;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RoomTests {

    @Autowired
    private RoomApi roomApi;

    @Test
	public void givenIAmAnAdmin_WhenISetupANewRoom() throws Exception {
		ResponseSpec response = roomApi.setupRoom();

        itShouldSetupANewRoom(response);
        itShouldAllocateANewId(response);
	}

    private void itShouldSetupANewRoom(ResponseSpec response) {
        response
            .expectStatus()
            .isCreated();
    }

    private void itShouldAllocateANewId(ResponseSpec response) {
        response
			.expectBody(RoomResponse.class)
				.value(room -> {
					assertThat(room.getId()).isNotEqualTo(new UUID(0, 0));
                    assertThat(room.getId()).isNotNull();
				});
    }
}
