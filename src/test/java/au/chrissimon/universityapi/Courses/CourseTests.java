package au.chrissimon.universityapi.Courses;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import au.chrissimon.universityapi.Rooms.RoomApi;
import au.chrissimon.universityapi.Rooms.RoomResponse;
import au.chrissimon.universityapi.Rooms.SetupRoomRequest;

import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.UUID;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CourseTests {

	@Autowired
	private CourseApi courseApi;
	@Autowired
	private RoomApi roomApi;

	@Test
	public void givenIAmAnAdmin_WhenIIncludeANewCourseInTheCatalog() throws Exception {
		RoomResponse roomResponse = roomApi.getRoomFromResponse(roomApi.setupRoom(new SetupRoomRequest("Test room", 5)));

		IncludeCourseRequest courseRequest = new IncludeCourseRequest("Test course", roomResponse.getId());

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
		assertThat(newCourse.getRoomId()).isEqualTo(courseRequest.getRoomId());
	}

	@ParameterizedTest
	@ValueSource(strings = {"Test Course", "Another Course"})
	public void givenIHaveIncludedACourse_WhenICheckTheCourseDetails(String courseName)
	{
		RoomResponse roomResponse = roomApi.getRoomFromResponse(roomApi.setupRoom(new SetupRoomRequest("Test room", 5)));

		IncludeCourseRequest courseRequest = new IncludeCourseRequest(courseName, roomResponse.getId());

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

	@Test
	public void givenIHaveNotSetupARoom_WhenIIncludeANewCourseInTheCatalog() throws Exception {
		IncludeCourseRequest courseRequest = new IncludeCourseRequest("Test course", null);

		ResponseSpec response = courseApi.includeNewCourseInCatalog(courseRequest);

		itShouldNotIncludeTheCourse(response);
	}

	private void itShouldNotIncludeTheCourse(ResponseSpec response) {
		response
			.expectStatus()
			.is4xxClientError();
	}

	@Test
	public void givenIHaveAnIncorrectRoomId_WhenIIncludeANewCourseInTheCatalog() throws Exception {
		IncludeCourseRequest courseRequest = new IncludeCourseRequest("Test course", UUID.randomUUID());

		ResponseSpec response = courseApi.includeNewCourseInCatalog(courseRequest);

		itShouldNotIncludeTheCourse(response);
	}
}