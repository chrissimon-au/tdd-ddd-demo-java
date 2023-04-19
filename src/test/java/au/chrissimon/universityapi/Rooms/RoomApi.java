package au.chrissimon.universityapi.Rooms;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import au.chrissimon.universityapi.Helpers;

@Lazy // To ensure local.server.port is set by the time it is wired.
@Component
public class RoomApi {

    @Value(value="${local.server.port}")
    private int port;

    private static final String ROOM_PATH = "/rooms";

    public URI uriForRoomId(UUID id) {
        return URI.create(Helpers.baseUri(port) + ROOM_PATH + "/" + id);
    }

    public ResponseSpec setupRoom(SetupRoomRequest roomRequest) {
		return Helpers.newWebClient(port)
			.post()
				.uri(ROOM_PATH)
                .bodyValue(roomRequest)
			.exchange();
	}

    public RoomResponse getRoomFromResponse(ResponseSpec response) {
        return response
            .expectBody(RoomResponse.class)
                .returnResult()
                .getResponseBody();
    }

    public ResponseSpec getRoom(URI roomUri) {
        return Helpers.newWebClient(port)
            .get()
                .uri(roomUri)
            .exchange();
    }

    public ResponseSpec getRoom(UUID id) {
        return getRoom(uriForRoomId(id));
    }

    public RoomResponse setupRoomAsEntity(SetupRoomRequest setupRoomRequest) {
        return getRoomFromResponse(setupRoom(setupRoomRequest));
    }
}
