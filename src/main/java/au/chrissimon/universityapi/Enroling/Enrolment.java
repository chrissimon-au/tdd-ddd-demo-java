package au.chrissimon.universityapi.Enroling;

import java.util.UUID;

public class Enrolment {
    private UUID id;
    private UUID studentId;

    Enrolment() { }

    Enrolment(UUID id, UUID studentId) {
        super();
        setId(id);
        setStudentId(studentId);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }
}
