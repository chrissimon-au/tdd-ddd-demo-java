package au.chrissimon.universityapi.Rooms;

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

    public ResponseSpec setupRoom() {
		return Helpers.newWebClient(port)
			.post()
				.uri(ROOM_PATH)
			.exchange();
	}

}
