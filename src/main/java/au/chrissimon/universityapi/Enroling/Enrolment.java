package au.chrissimon.universityapi.Enroling;

import java.util.UUID;

public class Enrolment {
    private UUID id;

    Enrolment(UUID id) {
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
