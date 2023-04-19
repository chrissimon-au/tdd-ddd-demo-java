package au.chrissimon.universityapi.Courses;

public class IncludeCourseRequest {
    private String name;

    public IncludeCourseRequest(String name) {
        super();
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}