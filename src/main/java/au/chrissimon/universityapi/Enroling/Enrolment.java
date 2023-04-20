package au.chrissimon.universityapi.Enroling;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Enrolment {
    private @Id UUID id;
    private UUID studentId;
    private UUID courseId;

    Enrolment() { }

    Enrolment(UUID id, UUID studentId, UUID courseId) {
        super();
        setId(id);
        setStudentId(studentId);
        setCourseId(courseId);
    }

    public UUID getId() {
        return id;
    }

    private void setId(UUID id) {
        this.id = id;
    }

    public UUID getStudentId() {
        return studentId;
    }

    private void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public UUID getCourseId() {
        return courseId;
    }

    private void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }
}
