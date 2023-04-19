package au.chrissimon.universityapi.Courses;

import java.util.UUID;

public class CourseResponse {
    private UUID id;
    private String name;
    private UUID roomId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CourseResponse () { }

    public CourseResponse (UUID id) {
        super();
        setId(id);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public static CourseResponse fakeCourse() {
        return new CourseResponse(UUID.randomUUID());
    }
}
