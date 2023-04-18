package au.chrissimon.universityapi.Enroling;

import java.util.UUID;

public class EnrolmentResponse {
    private UUID id;
    private UUID studentId;

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
