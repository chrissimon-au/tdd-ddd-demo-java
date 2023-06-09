package au.chrissimon.universityapi.Rooms;

import java.util.UUID;

public class RoomResponse {
    private UUID id;
	private String name;
	private int capacity;

	RoomResponse() {}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
