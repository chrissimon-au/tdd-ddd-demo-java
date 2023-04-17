package au.chrissimon.universityapi.Courses;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.beans.factory.annotation.Value;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CourseTests {

	@Value(value="${local.server.port}")
	private int port;

	@Test
	public void givenIAmAnAdmin_WhenIIncludeANewCourseInTheCatalog() throws Exception {
		ResponseSpec response = WebTestClient
			.bindToServer()
				.baseUrl("http://localhost:" + port)
				.build()
			.post()
				.uri("/courses")
			.exchange();

		itShouldIncludeTheCourseInTheCatalog(response);
	}

	private void itShouldIncludeTheCourseInTheCatalog(ResponseSpec response) {
		response
			.expectStatus()
			.isCreated();
	}
}