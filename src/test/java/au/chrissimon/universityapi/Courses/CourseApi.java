package au.chrissimon.universityapi.Courses;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import au.chrissimon.universityapi.Helpers;

@Lazy // To ensure local.server.port is set by the time it is wired.
@Component
public class CourseApi {
	@Value(value="${local.server.port}")
	private int port;

	public ResponseSpec includeNewCourseInCatalog() {
		return Helpers.newWebClient(port)
			.post()
				.uri("/courses")
			.exchange();
	}

	public CourseResponse getCourseFromResponse(ResponseSpec response) {
		return response
			.expectBody(CourseResponse.class)
				.returnResult()
				.getResponseBody();
	}
}
