package au.chrissimon.universityapi.Rooms;

import java.util.UUID;

public class Room {
    private UUID id;

    public Room(UUID id) {
        super();
        setId(id);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
