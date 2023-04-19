package au.chrissimon.universityapi.Courses;

import java.util.Optional;
import java.util.UUID;

import au.chrissimon.universityapi.Enroling.Enrolment;
import au.chrissimon.universityapi.Rooms.Room;
import au.chrissimon.universityapi.Students.Student;
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

    public static Optional<Course> includeInCatalog(String name, Optional<Room> room) {
        return room
            .map(r -> {
                return new Course(UUID.randomUUID(), name, r.getId());
            });
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public Enrolment Enrol(Student student) {
        // Unable to implement rules as we don't have access to the room capacity, or the current number of enrolments.
        return null;
    }
}
