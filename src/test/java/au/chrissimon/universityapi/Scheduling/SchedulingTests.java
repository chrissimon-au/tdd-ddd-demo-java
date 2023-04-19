package au.chrissimon.universityapi.Scheduling;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import au.chrissimon.universityapi.Courses.CourseApi;
import au.chrissimon.universityapi.Courses.CourseResponse;
import au.chrissimon.universityapi.Courses.IncludeCourseRequest;
import au.chrissimon.universityapi.Enroling.EnrolmentApi;
import au.chrissimon.universityapi.Rooms.RoomApi;
import au.chrissimon.universityapi.Rooms.RoomResponse;
import au.chrissimon.universityapi.Rooms.SetupRoomRequest;
import au.chrissimon.universityapi.Students.RegisterStudentRequest;
import au.chrissimon.universityapi.Students.StudentApi;
import au.chrissimon.universityapi.Students.StudentResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SchedulingTests {

    @Autowired
    private ScheduleApi scheduleApi;

    @Autowired
    private RoomApi roomApi;

    @Autowired
    private StudentApi studentApi;

    @Autowired
    private CourseApi courseApi;

    @Autowired
    private EnrolmentApi enrolmentApi;

    @Test
    public void givenIAmAnAdmin_WhenIScheduleCourses() {
        StudentResponse student = studentApi.getStudentFromResponse(studentApi.registerStudent(new RegisterStudentRequest("Test Student")));

        CourseResponse course = courseApi.getCourseFromResponse(courseApi.includeNewCourseInCatalog(new IncludeCourseRequest("Scheduling Test Course")));

        enrolmentApi.enrolStudentInCourse(student, course);

        RoomResponse room = roomApi.getRoomFromResponse(roomApi.setupRoom(new SetupRoomRequest("Scheduling Test Room", 3)));

        ResponseSpec response = scheduleApi.schedule();

        itShouldScheduleTheCourses(response);

        ScheduleResponse schedule = scheduleApi.getScheduleFromResponse(response);

        itShouldListTheScheduledCourses(schedule, course, room);
    }

    private void itShouldScheduleTheCourses(ResponseSpec response) {
        response
            .expectStatus()
            .isOk();
    }

    private void itShouldListTheScheduledCourses(ScheduleResponse response, CourseResponse course, RoomResponse room) {
        assertThat(response.scheduledCourses).hasSize(1);

        response.scheduledCourses.forEach(scheduledCourse -> {
            assertThat(scheduledCourse.getId()).isEqualTo(course.getId());
            assertThat(scheduledCourse.getName()).isEqualTo(course.getName());
            assertThat(scheduledCourse.getRoomId()).isEqualTo(room.getId());
        });
    }

}
