package au.chrissimon.universityapi.Students;

import java.util.UUID;

public class StudentResponse	{
	private UUID id;
	private String name;
	
	StudentResponse() {}

	StudentResponse(UUID id) {
		super();
		setId(id);
	}

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

	public static StudentResponse fakeStudent() {
		return new StudentResponse(UUID.randomUUID());
	}
}