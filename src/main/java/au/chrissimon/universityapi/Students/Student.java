package au.chrissimon.universityapi.Students;

import java.util.UUID;

public class Student {
    private UUID id;

    public Student(UUID id) {
        setId(id);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public static Student register()
    {
        return new Student(UUID.randomUUID());
    }
}
