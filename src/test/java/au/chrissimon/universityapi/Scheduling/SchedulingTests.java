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

import java.util.List;

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
    public void givenThereIsASingleCourseAndRoom_WhenIScheduleCourses() {
        StudentResponse student = studentApi.registerStudentAsEntity(new RegisterStudentRequest("Test Student"));

        CourseResponse course = courseApi.includeNewCourseInCatalogAsEntity(new IncludeCourseRequest("Scheduling Test Course"));

        enrolmentApi.enrolStudentInCourse(student, course);

        RoomResponse room = roomApi.setupRoomAsEntity(new SetupRoomRequest("Scheduling Test Room", 3));

        ResponseSpec response = scheduleApi.schedule();

        itShouldScheduleTheCourses(response);

        ScheduleResponse schedule = scheduleApi.getScheduleFromResponse(response);

        itShouldScheduledCourseToRoom(schedule, course, room);
    }

    private void itShouldScheduleTheCourses(ResponseSpec response) {
        response
            .expectStatus()
            .isOk();
    }

    private void itShouldScheduledCourseToRoom(ScheduleResponse response, CourseResponse course, RoomResponse room) {
        List<CourseResponse> courseInScheduledList = response.scheduledCourses.stream().filter(c -> c.getId().equals(course.getId())).toList();

        assertThat(courseInScheduledList).hasSize(1);
           
        CourseResponse scheduledCourse = courseInScheduledList.get(0);
            
        assertThat(scheduledCourse.getName()).isEqualTo(course.getName());
        assertThat(scheduledCourse.getRoomId()).isEqualTo(room.getId());
    }
}
