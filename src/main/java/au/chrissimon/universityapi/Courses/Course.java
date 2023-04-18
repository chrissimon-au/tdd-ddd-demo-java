package au.chrissimon.universityapi.Courses;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Course {
    private @Id UUID id;
	private String name;

	Course() { }

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
