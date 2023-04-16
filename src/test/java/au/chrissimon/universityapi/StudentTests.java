package au.chrissimon.universityapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.beans.factory.annotation.Value;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.time.Duration;
import java.util.UUID;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StudentTests {

	@Value(value="${local.server.port}")
	private int port;

	private static final String STUDENT_PATH = "/students";

	private String baseUri() { return "http://localhost:" + port; }

	private WebTestClient newWebClient() {
		return WebTestClient
			.bindToServer()
				.baseUrl(baseUri())
				.responseTimeout(Duration.ofSeconds(90))
				.build();
	}

	private ResponseSpec registerStudent(RegisterStudentRequest studentRequest) {
		return newWebClient()
			.post()
				.uri(STUDENT_PATH)
				.bodyValue(studentRequest)
			.exchange();
	}

	@Test
	public void givenIAmAStudent_WhenIRegister() throws Exception {
		RegisterStudentRequest studentRequest = new RegisterStudentRequest("Test Student");

		ResponseSpec response = registerStudent(studentRequest);

		itShouldRegisterANewStudent(response);
		StudentResponse newStudent = itShouldAllocateANewId(response);
		itShouldShowWhereToLocateNewStudent(response, newStudent);
		itShouldConfirmStudentDetails(studentRequest, newStudent);
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
				.location(baseUri() + STUDENT_PATH + "/" + newStudent.getId());
	}

	private void itShouldConfirmStudentDetails(RegisterStudentRequest studentRequest, StudentResponse newStudent) {
		assertThat(newStudent.getName()).isEqualTo(studentRequest.getName());
	}

	@ParameterizedTest
	@ValueSource(strings = {"Test Student", "Another Student"})
	public void givenIHaveRegistered_WhenICheckMyDetails(String studentName)
	{
		RegisterStudentRequest studentRequest = new RegisterStudentRequest(studentName);

		URI newStudentLocation = registerStudent(studentRequest)
				.expectBody(StudentResponse.class)
				.returnResult()
				.getResponseHeaders().getLocation();

		ResponseSpec response = newWebClient()
			.get()
				.uri(newStudentLocation)
			.exchange();
			
		itShouldFindTheNewStudent(response);
		itShouldConfirmTheNewStudentsDetails(studentRequest, response);
	}

	private void itShouldFindTheNewStudent(ResponseSpec response) {
		response
			.expectStatus()
			.isOk();
	}

	private void itShouldConfirmTheNewStudentsDetails(RegisterStudentRequest studentRequest, ResponseSpec response) {
		response
			.expectBody(StudentResponse.class)
			.value(student -> {
				assertThat(student.getName()).isEqualTo(studentRequest.getName());
			});
	}
}