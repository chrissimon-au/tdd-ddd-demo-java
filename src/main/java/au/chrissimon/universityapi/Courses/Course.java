package au.chrissimon.universityapi.Courses;

import java.util.UUID;

public class Course {
    private UUID id;
	private String name;

	Course(UUID id, String name) {
		super();
		setId(id);
		setName(name);
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

    public static Course includeInCatalog(String name) {
        return new Course(UUID.randomUUID(), name);
    }
}
