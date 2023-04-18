package au.chrissimon.universityapi.Courses;

import java.util.UUID;

public class CourseResponse {
    private UUID id;
    private String name;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CourseResponse () { }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
