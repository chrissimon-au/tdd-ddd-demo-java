package au.chrissimon.universityapi.Students;

import java.util.UUID;

public class Student {
    private UUID id;
    private String name;

    public Student() {}

    public Student(UUID id, String name) {
        super();
        setId(id);
        setName(name);
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

    public static Student register(String name)
    {
        return new Student(UUID.randomUUID(), name);
    }
}
