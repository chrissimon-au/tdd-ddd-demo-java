package au.chrissimon.universityapi.Rooms;

import java.util.UUID;

public class RoomResponse {
    private UUID id;

	RoomResponse() {}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}
