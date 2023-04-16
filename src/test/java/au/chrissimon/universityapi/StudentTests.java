package au.chrissimon.universityapi;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.beans.factory.annotation.Value;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StudentTests {

	@Value(value="${local.server.port}")
	private int port;

	private String baseUri() { return "http://localhost:" + port; }

	@Test
	public void givenIAmAStudent_WhenIRegister() throws Exception {
		ResponseSpec response = WebTestClient
			.bindToServer()
				.baseUrl(baseUri())
				.build()
			.post()
				.uri("/students")
			.exchange();

		itShouldRegisterANewStudent(response);
		StudentResponse newStudent = itShouldAllocateANewId(response);
		itShouldShowWhereToLocateNewStudent(response, newStudent);
	}

	private void itShouldRegisterANewStudent(ResponseSpec response) {
		response
			.expectStatus()
			.isCreated();
	}

	private StudentResponse itShouldAllocateANewId(ResponseSpec response) {
		return response
			.expectBody(StudentResponse.class)
				.value(student -> {
					assertThat(student.getId()).isNotEqualTo(new UUID(0, 0));
					assertThat(student.getId()).isNotNull();

				})
				.returnResult()
				.getResponseBody();
	}

	private void itShouldShowWhereToLocateNewStudent(ResponseSpec response, StudentResponse newStudent) {
		response
			.expectHeader()
				.location(baseUri() + "/students" + "/" + newStudent.getId());
	}
}