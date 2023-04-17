package au.chrissimon.universityapi.Rooms;

public class SetupRoomRequest {
	private String name;
	private int capacity;

	SetupRoomRequest() {}

	SetupRoomRequest(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public void setId(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
