package au.chrissimon.universityapi.Enroling;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import au.chrissimon.universityapi.Courses.CourseApi;
import au.chrissimon.universityapi.Courses.CourseResponse;
import au.chrissimon.universityapi.Courses.IncludeCourseRequest;
import au.chrissimon.universityapi.Rooms.SetupRoomRequest;
import au.chrissimon.universityapi.Students.RegisterStudentRequest;
import au.chrissimon.universityapi.Students.StudentApi;
import au.chrissimon.universityapi.Students.StudentResponse;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EnrolingTests {
    
    @Autowired
    private CourseApi courseApi;

    @Autowired
    private StudentApi studentApi;

    @Autowired
    private EnrolmentApi enrolmentApi;

    @Test
    public void givenIAmARegisteredStudent_WhenIEnrolInACourse()
    {
        SetupRoomRequest roomRequest = new SetupRoomRequest("Test Room", 5);
        IncludeCourseRequest courseRequest = new IncludeCourseRequest("Test Course");

        CourseResponse course = courseApi.getCourseFromResponse(courseApi.includeNewCourseInCatalog(roomRequest, courseRequest));

        RegisterStudentRequest studentRequest = new RegisterStudentRequest("Test student");
        StudentResponse student = studentApi.getStudentFromResponse(studentApi.registerStudent(studentRequest));

        ResponseSpec enrolmentResponse = enrolmentApi.enrolStudentInCourse(student, course);

        itShouldEnrolMe(enrolmentResponse);
        EnrolmentResponse newEnrolment = enrolmentApi.getEnrolmentFromResponse(enrolmentResponse);
        itShouldAllocateANewEnrolmentId(newEnrolment);
    }

    private void itShouldEnrolMe(ResponseSpec enrolmentResponse) {
        enrolmentResponse
            .expectStatus()
            .isCreated();
    }


    private void itShouldAllocateANewEnrolmentId(EnrolmentResponse enrolment) {
        assertThat(enrolment.getId()).isNotEqualTo(new UUID(0, 0));
        assertThat(enrolment.getId()).isNotNull();
    }

}
