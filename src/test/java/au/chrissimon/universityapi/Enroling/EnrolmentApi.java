package au.chrissimon.universityapi.Enroling;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import au.chrissimon.universityapi.Helpers;
import au.chrissimon.universityapi.Courses.CourseResponse;
import au.chrissimon.universityapi.Students.StudentResponse;

@Lazy // To ensure local.server.port is set by the time it is wired.
@Component
public class EnrolmentApi {

    @Value(value="${local.server.port}")
    private int port;

    public ResponseSpec enrolStudentInCourse(StudentResponse student, CourseResponse course) {
        return Helpers.newWebClient(port)
            .post()
                .uri("/students/" + student.getId() + "/courses")
            .exchange();
    }
}
