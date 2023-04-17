package au.chrissimon.universityapi.Courses;

import java.util.UUID;

public class Course {
    private UUID id;

	Course(UUID id) {
		super();
		setId(id);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

    public static Course includeInCatalog() {
        return new Course(UUID.randomUUID());
    }
}
