package au.chrissimon.universityapi.Scheduling;

import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import au.chrissimon.universityapi.Courses.Course;
import au.chrissimon.universityapi.Rooms.Room;

import static org.assertj.core.api.Assertions.assertThat;

public class SchedulerTests {

    @Test
    public void whenScheduling() {
    
        Scheduler scheduler = new Scheduler();
        
        Course course = Course.includeInCatalog("Test");
        CourseEnrolments courseEnrolments = new CourseEnrolments(course, 2);
        Set<CourseEnrolments> coursesEnrolments = Set.of(courseEnrolments);
        Room room = new Room(UUID.randomUUID(), "Test", 2);
        Set<Room> rooms = Set.of(room);

        Schedule schedule = scheduler.scheduleCourses(coursesEnrolments, rooms);

        Set<Course> scheduledCourses = schedule.getScheduledCourses();

        assertThat(scheduledCourses.size()).isEqualTo(1);

        scheduledCourses.forEach(c -> {
            assertThat(c.getId()).isEqualTo(courseEnrolments.getCourse().getId());
            assertThat(c.getRoomId()).isEqualTo(room.getId());
        });
    }

}
