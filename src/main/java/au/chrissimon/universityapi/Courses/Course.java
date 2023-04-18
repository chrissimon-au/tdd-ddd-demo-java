package au.chrissimon.universityapi.Courses;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Course {
    private @Id UUID id;
    private String name;
    @NotNull
    private UUID roomId;

    Course() { }

    Course(UUID id, String name, UUID roomId) {
        super();
        setId(id);
        setName(name);
        setRoomId(roomId);
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

    public static Course includeInCatalog(String name, UUID roomId) {
        return new Course(UUID.randomUUID(), name, roomId);
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }
}
