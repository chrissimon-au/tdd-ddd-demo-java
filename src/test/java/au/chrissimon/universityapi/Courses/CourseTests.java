package au.chrissimon.universityapi.Courses;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CourseTests {

	@Autowired
	private CourseApi courseApi;

	@Test
	public void givenIAmAnAdmin_WhenIIncludeANewCourseInTheCatalog() throws Exception {
		ResponseSpec response = courseApi.includeNewCourseInCatalog();

		itShouldIncludeTheCourseInTheCatalog(response);
		CourseResponse newCourse = courseApi.getCourseFromResponse(response);
		itShouldAllocateANewId(newCourse);
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
}