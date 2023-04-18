package au.chrissimon.universityapi.Courses;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import au.chrissimon.universityapi.Helpers;
import au.chrissimon.universityapi.Rooms.RoomApi;
import au.chrissimon.universityapi.Rooms.RoomResponse;
import au.chrissimon.universityapi.Rooms.SetupRoomRequest;

@Lazy // To ensure local.server.port is set by the time it is wired.
@Component
public class CourseApi {

	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private RoomApi roomApi;

	private static final String COURSE_PATH = "/courses";

	public ResponseSpec includeNewCourseInCatalog(SetupRoomRequest roomRequest, IncludeCourseRequest courseRequest) {
		RoomResponse roomResponse = roomApi.getRoomFromResponse(roomApi.setupRoom(roomRequest));

		courseRequest.setRoomId(roomResponse.getId());

		return includeNewCourseInCatalog(courseRequest);
	}

	public ResponseSpec includeNewCourseInCatalog(IncludeCourseRequest courseRequest) {
		return Helpers.newWebClient(port)
			.post()
				.uri(COURSE_PATH)
				.bodyValue(courseRequest)
			.exchange();
	}

	public CourseResponse getCourseFromResponse(ResponseSpec response) {
		return response
			.expectBody(CourseResponse.class)
				.returnResult()
				.getResponseBody();
	}

	public URI uriForCourseId(UUID id) {
		return URI.create(Helpers.baseUri(port) + COURSE_PATH + "/" + id);
	}

    public ResponseSpec getCourse(URI newCourseLocation) {
        return Helpers.newWebClient(port)
			.get()
				.uri(newCourseLocation)
			.exchange();
    }
}
