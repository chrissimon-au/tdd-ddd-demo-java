package au.chrissimon.universityapi.Students;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import au.chrissimon.universityapi.Helpers;

@Lazy // To ensure local.server.port is set by the time it is wired.
@Component
public class StudentApi {

    @Value(value="${local.server.port}")
    private int port;

    private static final String STUDENT_PATH = "/students";

    public URI uriForStudentId(UUID id) {
        return URI.create(Helpers.baseUri(port) + STUDENT_PATH + "/" + id);
    }

    public ResponseSpec registerStudent(RegisterStudentRequest studentRequest) {
		return Helpers.newWebClient(port)
			.post()
				.uri(STUDENT_PATH)
				.bodyValue(studentRequest)
			.exchange();
	}

    public StudentResponse registerStudentAsEntity(RegisterStudentRequest studentRequest) {
        return getStudentFromResponse(registerStudent(studentRequest));
    }

    public ResponseSpec getStudent(UUID id) {
        return getStudent(uriForStudentId(id));
    }

    public ResponseSpec getStudent(URI studentUri) {
        return Helpers.newWebClient(port)
			.get()
				.uri(studentUri)
			.exchange();
    }

    public StudentResponse getStudentFromResponse(ResponseSpec response) {
        return response
            .expectBody(StudentResponse.class)
                .returnResult()
                .getResponseBody();
    }
}
