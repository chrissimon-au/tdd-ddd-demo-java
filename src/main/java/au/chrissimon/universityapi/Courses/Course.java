package au.chrissimon.universityapi.Courses;

import java.util.UUID;

import au.chrissimon.universityapi.Rooms.Room;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Course {
    private @Id UUID id;
    private String name;
    private UUID roomId;

    public Course() { }

    Course(UUID id, String name) {
        super();
        setId(id);
        setName(name);
    }

    public UUID getId() {
        return id;
    }

    private void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public UUID getRoomId() {
        return roomId;
    }

    private void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public static Course includeInCatalog(String name) {
        return new Course(UUID.randomUUID(), name);
    }

    public void assignTo(Room r) {
        setRoomId(r.getId());
    }
}
