package au.chrissimon.universityapi.Rooms;

import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Room {
    private @Id UUID id;
    private String name;
    private int capacity;

    public Room() { }
    
    public Room(UUID id, String name, int capacity) {
        super();
        setId(id);
        setName(name);
        setCapacity(capacity);
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public static Room setupNew(Room roomRequest) {
        return new Room(UUID.randomUUID(), roomRequest.getName(), roomRequest.getCapacity());
    }

    public Optional<Room> roomIfCapacityForEnrolment(long numEnrolments) {
        if (numEnrolments + 1 > getCapacity()) {
            return Optional.empty();
        };
        return Optional.of(this);
    }
}
