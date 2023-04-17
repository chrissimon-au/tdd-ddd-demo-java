package au.chrissimon.universityapi.Courses;

import java.util.UUID;

public class CourseResponse {
    private UUID id;
    
    public CourseResponse () { }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
