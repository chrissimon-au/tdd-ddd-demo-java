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
        SetupRoomRequest roomRequest = new SetupRoomRequest("Test Room", 5);

		ResponseSpec response = roomApi.setupRoom(roomRequest);

        itShouldSetupANewRoom(response);
        RoomResponse newRoom = roomApi.getRoomFromResponse(response);
        itShouldAllocateANewId(newRoom);
        itShouldShowWhereToLocateNewRoom(response, newRoom);
        itShouldConfirmRoomDetails(roomRequest, newRoom);
	}

    private void itShouldSetupANewRoom(ResponseSpec response) {
        response
            .expectStatus()
            .isCreated();
    }

    private void itShouldAllocateANewId(RoomResponse room) {
    	assertThat(room.getId()).isNotEqualTo(new UUID(0, 0));
        assertThat(room.getId()).isNotNull();
    }

    private void itShouldShowWhereToLocateNewRoom(ResponseSpec response, RoomResponse newRoom) {
		response
			.expectHeader()
				.location(roomApi.uriForRoomId(newRoom.getId()).toString());
	}

    private void itShouldConfirmRoomDetails(SetupRoomRequest roomRequest, RoomResponse newRoom) {
		assertThat(newRoom.getName()).isEqualTo(roomRequest.getName());
        assertThat(newRoom.getCapacity()).isEqualTo(roomRequest.getCapacity());
	}
}
