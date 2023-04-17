package au.chrissimon.universityapi.Rooms;

import java.util.UUID;

public class Room {
    private UUID id;
    private String name;
    private int capacity;
    
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
}
