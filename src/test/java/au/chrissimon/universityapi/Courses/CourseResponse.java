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
}
