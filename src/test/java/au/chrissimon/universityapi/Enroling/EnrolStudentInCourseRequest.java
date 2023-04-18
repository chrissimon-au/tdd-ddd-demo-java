package au.chrissimon.universityapi.Enroling;

import java.util.UUID;

public class EnrolStudentInCourseRequest {
    private UUID courseId;

    EnrolStudentInCourseRequest(UUID courseId) {
        super();
        setCourseId(courseId);
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }
}
