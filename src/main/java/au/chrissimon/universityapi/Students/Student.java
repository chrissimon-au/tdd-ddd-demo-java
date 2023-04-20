package au.chrissimon.universityapi.Students;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {
    private @Id UUID id;
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

    private void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public static Student register(String name)
    {
        return new Student(UUID.randomUUID(), name);
    }
}
