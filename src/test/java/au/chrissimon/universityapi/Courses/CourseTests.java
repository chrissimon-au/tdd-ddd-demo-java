package au.chrissimon.universityapi.Courses;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.UUID;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CourseTests {

	@Autowired
	private CourseApi courseApi;

	@Test
	public void givenIAmAnAdmin_WhenIIncludeANewCourseInTheCatalog() throws Exception {
		IncludeCourseRequest courseRequest = new IncludeCourseRequest("Test course");

		ResponseSpec response = courseApi.includeNewCourseInCatalog(courseRequest);

		itShouldIncludeTheCourseInTheCatalog(response);
		CourseResponse newCourse = courseApi.getCourseFromResponse(response);
		itShouldAllocateANewId(newCourse);
		itShouldShowWhereToLocateNewCourse(response, newCourse);
		itShouldConfirmCourseDetails(courseRequest, newCourse);
	}

	private void itShouldIncludeTheCourseInTheCatalog(ResponseSpec response) {
		response
			.expectStatus()
			.isCreated();
	}

	private void itShouldAllocateANewId(CourseResponse course) {
		assertThat(course.getId()).isNotEqualTo(new UUID(0, 0));
		assertThat(course.getId()).isNotNull();
	}

	private void itShouldShowWhereToLocateNewCourse(ResponseSpec response, CourseResponse newCourse) {
		response
			.expectHeader()
				.location(courseApi.uriForCourseId(newCourse.getId()).toString());
	}

	private void itShouldConfirmCourseDetails(IncludeCourseRequest courseRequest, CourseResponse newCourse) {
		assertThat(newCourse.getName()).isEqualTo(courseRequest.getName());
	}

	@ParameterizedTest
	@ValueSource(strings = {"Test Course", "Another Course"})
	public void givenIHaveIncludedACourse_WhenICheckTheCourseDetails(String courseName)
	{
		IncludeCourseRequest courseRequest = new IncludeCourseRequest(courseName);

		URI newCourseLocation = courseApi.includeNewCourseInCatalog(courseRequest)
				.expectBody(CourseResponse.class)
				.returnResult()
				.getResponseHeaders().getLocation();

		ResponseSpec response = courseApi.getCourse(newCourseLocation);
			
		itShouldFindTheNewCourse(response);
		CourseResponse course = courseApi.getCourseFromResponse(response);
		itShouldConfirmCourseDetails(courseRequest, course);
	}

	private void itShouldFindTheNewCourse(ResponseSpec response) {
		response
			.expectStatus()
			.isOk();
	}
}