package au.chrissimon.universityapi.Enroling;

import java.net.URI;
import java.util.UUID;

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

    public static final String ENROLMENT_PATH = "/enrolments";

    public ResponseSpec enrolStudentInCourse(StudentResponse student, CourseResponse course) {
        EnrolStudentInCourseRequest enrolStudentInCourseRequest = new EnrolStudentInCourseRequest(course.getId());
        return Helpers.newWebClient(port)
            .post()
                .uri("/students/" + student.getId() + "/courses")
                .bodyValue(enrolStudentInCourseRequest)
            .exchange();
    }

    public EnrolmentResponse getEnrolmentFromResponse(ResponseSpec enrolmentResponse) {
        return enrolmentResponse
            .expectBody(EnrolmentResponse.class)
            .returnResult()
            .getResponseBody();
    }

    public URI uriForEnrolmentId(UUID id) {
        return URI.create(Helpers.baseUri(port) + ENROLMENT_PATH + "/" + id);
    }
}
