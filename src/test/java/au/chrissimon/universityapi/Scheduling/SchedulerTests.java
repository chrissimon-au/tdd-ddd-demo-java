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
        CourseEnrolments courseEnrolments = new CourseEnrolments(course, 2);
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
}
