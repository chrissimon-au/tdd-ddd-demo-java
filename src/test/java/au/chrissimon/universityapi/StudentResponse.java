package au.chrissimon.universityapi;

import java.util.UUID;

public class StudentResponse	{
	private UUID id;
	
	StudentResponse() {}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}