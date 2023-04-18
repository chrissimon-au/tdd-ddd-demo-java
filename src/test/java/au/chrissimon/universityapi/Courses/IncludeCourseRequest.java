package au.chrissimon.universityapi.Courses;

import java.util.UUID;

public class IncludeCourseRequest {
    private String name;
    private UUID roomId;

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

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }
}