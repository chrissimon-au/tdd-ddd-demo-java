package au.chrissimon.universityapi.Enroling;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import au.chrissimon.universityapi.Courses.CourseApi;
import au.chrissimon.universityapi.Courses.CourseResponse;
import au.chrissimon.universityapi.Courses.IncludeCourseRequest;
import au.chrissimon.universityapi.Students.RegisterStudentRequest;
import au.chrissimon.universityapi.Students.StudentApi;
import au.chrissimon.universityapi.Students.StudentResponse;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
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
        IncludeCourseRequest courseRequest = new IncludeCourseRequest("Test Course");

        CourseResponse course = courseApi.includeNewCourseInCatalogAsEntity(courseRequest);

        RegisterStudentRequest studentRequest = new RegisterStudentRequest("Test student");
        StudentResponse student = studentApi.registerStudentAsEntity(studentRequest);

        ResponseSpec enrolmentResponse = enrolmentApi.enrolStudentInCourse(student, course);

        itShouldEnrolMe(enrolmentResponse);
        EnrolmentResponse newEnrolment = enrolmentApi.getEnrolmentFromResponse(enrolmentResponse);
        itShouldAllocateANewEnrolmentId(newEnrolment);
        itShouldConfirmEnrolmentDetails(student, course, newEnrolment);
        itShouldShowWhereToCheckEnrolmentDetails(enrolmentResponse, newEnrolment);
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

    private void itShouldConfirmEnrolmentDetails(StudentResponse student, CourseResponse course, EnrolmentResponse enrolment) {
        assertThat(enrolment.getStudentId()).isEqualTo(student.getId());
        assertThat(enrolment.getCourseId()).isEqualTo(course.getId());
    }

    private void itShouldShowWhereToCheckEnrolmentDetails(ResponseSpec response, EnrolmentResponse enrolment) {
        response
			.expectHeader()
				.location(enrolmentApi.uriForEnrolmentId(enrolment.getId()).toString());
    }

    @Test
    public void givenIHaveTheWrongStudentId_WhenIEnrolInACourse()
    {
        IncludeCourseRequest courseRequest = new IncludeCourseRequest("Test Course");

        CourseResponse course = courseApi.includeNewCourseInCatalogAsEntity(courseRequest);

        StudentResponse student = StudentResponse.fakeStudent();

        ResponseSpec enrolmentResponse = enrolmentApi.enrolStudentInCourse(student, course);

        itShouldNotEnrolMeWithNotFound(enrolmentResponse);
    }

    private void itShouldNotEnrolMeWithNotFound(ResponseSpec enrolmentResponse) {
        enrolmentResponse
            .expectStatus()
            .isNotFound();
    }

    @Test
    public void givenIHaveTheWrongCourseId_WhenIEnrolInACourse()
    {
        CourseResponse course = CourseResponse.fakeCourse();

        RegisterStudentRequest studentRequest = new RegisterStudentRequest("Test student");
        StudentResponse student = studentApi.registerStudentAsEntity(studentRequest);

        ResponseSpec enrolmentResponse = enrolmentApi.enrolStudentInCourse(student, course);

        itShouldNotEnrolMeWithError(enrolmentResponse);
    }

    private void itShouldNotEnrolMeWithError(ResponseSpec enrolmentResponse) {
        enrolmentResponse
            .expectStatus()
            .isBadRequest();
    }

    @Test
    public void givenIHaveEnroled_WhenICheckMyEnrolment()
    {
        IncludeCourseRequest courseRequest = new IncludeCourseRequest("Test Course");

        CourseResponse course = courseApi.includeNewCourseInCatalogAsEntity(courseRequest);

        RegisterStudentRequest studentRequest = new RegisterStudentRequest("Test student");
        StudentResponse student = studentApi.registerStudentAsEntity(studentRequest);

        URI newEnrolmentLocation = enrolmentApi.enrolStudentInCourse(student, course)
            .expectBody(EnrolmentResponse.class)
            .returnResult()
            .getResponseHeaders().getLocation();

        ResponseSpec response = enrolmentApi.getEnrolment(newEnrolmentLocation);

        EnrolmentResponse enrolment = enrolmentApi.getEnrolmentFromResponse(response);

        itShouldFindTheEnrolment(response);
        itShouldConfirmEnrolmentDetails(student, course, enrolment);
    }

    public void itShouldFindTheEnrolment(ResponseSpec response) {
        response
            .expectStatus()
            .isOk();
    }
}
