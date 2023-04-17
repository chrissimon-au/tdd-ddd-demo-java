package au.chrissimon.universityapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.UUID;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StudentTests {

	@Autowired
	private StudentApi studentApi;

	@Test
	public void givenIAmAStudent_WhenIRegister() throws Exception {
		RegisterStudentRequest studentRequest = new RegisterStudentRequest("Test Student");

		ResponseSpec response = studentApi.registerStudent(studentRequest);

		itShouldRegisterANewStudent(response);
		StudentResponse newStudent = studentApi.getStudentFromResponse(response);
		itShouldAllocateANewId(newStudent);
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

	private void itShouldAllocateANewId(StudentResponse newStudent) {
		assertThat(newStudent.getId()).isNotEqualTo(new UUID(0, 0));
		assertThat(newStudent.getId()).isNotNull();
	}

	private void itShouldShowWhereToLocateNewStudent(ResponseSpec response, StudentResponse newStudent) {
		response
			.expectHeader()
				.location(studentApi.uriForStudentId(newStudent.getId()).toString());
	}

	private void itShouldConfirmStudentDetails(RegisterStudentRequest studentRequest, StudentResponse newStudent) {
		assertThat(newStudent.getName()).isEqualTo(studentRequest.getName());
	}

	@ParameterizedTest
	@ValueSource(strings = {"Test Student", "Another Student"})
	public void givenIHaveRegistered_WhenICheckMyDetails(String studentName)
	{
		RegisterStudentRequest studentRequest = new RegisterStudentRequest(studentName);

		URI newStudentLocation = studentApi.registerStudent(studentRequest)
				.expectBody(StudentResponse.class)
				.returnResult()
				.getResponseHeaders().getLocation();

		ResponseSpec response = studentApi.getStudent(newStudentLocation);
			
		itShouldFindTheNewStudent(response);
		StudentResponse student = studentApi.getStudentFromResponse(response);
		itShouldConfirmStudentDetails(studentRequest, student);
	}

	private void itShouldFindTheNewStudent(ResponseSpec response) {
		response
			.expectStatus()
			.isOk();
	}

	@Test
	public void givenIHaveTheWrongId_WhenICheckMyDetails()
	{
		UUID wrongId = UUID.randomUUID();

		ResponseSpec response = studentApi.getStudent(wrongId);

		itShouldNotFindTheStudent(response);
	}

	private void itShouldNotFindTheStudent(ResponseSpec response) {
		response
			.expectStatus()
			.isNotFound();
	}
}