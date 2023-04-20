package au.chrissimon.universityapi.Scheduling;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import au.chrissimon.universityapi.Courses.Course;
import au.chrissimon.universityapi.Rooms.Room;

import static org.assertj.core.api.Assertions.assertThat;

public class SchedulerTests {

    @Test
    public void givenSingleCourseSingleRoom_whenScheduling() {
    
        Scheduler scheduler = new Scheduler();
        
        Course course = Course.includeInCatalog("Test");
        CourseEnrolments courseEnrolments = new CourseEnrolments(course, 2L);
        Collection<CourseEnrolments> coursesEnrolments = List.of(courseEnrolments);
        Room room = new Room(UUID.randomUUID(), "Test", 2);
        Collection<Room> rooms = List.of(room);

        Schedule schedule = scheduler.scheduleCourses(coursesEnrolments, rooms);

        Collection<Course> scheduledCourses = schedule.getScheduledCourses();

        assertThat(scheduledCourses.size()).isEqualTo(1);

        scheduledCourses.forEach(c -> {
            assertThat(c.getId()).isEqualTo(courseEnrolments.getCourse().getId());
            assertThat(c.getRoomId()).isEqualTo(room.getId());
        });
    }

    @Test
    public void givenTwoCoursesTwoRooms_whenScheduling() {
    
        Scheduler scheduler = new Scheduler();
        
        Course course1 = Course.includeInCatalog("1");
        CourseEnrolments courseEnrolment1 = new CourseEnrolments(course1, 4L);
        Course course2 = Course.includeInCatalog("2");
        CourseEnrolments courseEnrolment2 = new CourseEnrolments(course2, 2L);

        Collection<CourseEnrolments> coursesEnrolments = List.of(courseEnrolment1, courseEnrolment2);

        Room room1 = new Room(UUID.randomUUID(), "1", 2);
        Room room2 = new Room(UUID.randomUUID(), "2", 4);
        Collection<Room> rooms = List.of(room1, room2);

        Schedule schedule = scheduler.scheduleCourses(coursesEnrolments, rooms);

        Collection<Course> scheduledCourses = schedule.getScheduledCourses();

        assertThat(scheduledCourses.size()).isEqualTo(2);

        assertThat(scheduledCourses).contains(course1);
        assertThat(scheduledCourses).contains(course2);

        assertThat(course1.getRoomId()).isEqualTo(room2.getId());
        assertThat(course2.getRoomId()).isEqualTo(room1.getId());
    }
}
