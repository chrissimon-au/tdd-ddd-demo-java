package au.chrissimon.universityapi.Scheduling;

import java.util.Collection;
import java.util.HashSet;

import au.chrissimon.universityapi.Courses.Course;
import au.chrissimon.universityapi.Rooms.Room;

public class Scheduler {

    public Schedule scheduleCourses(Collection<CourseEnrolments> courseEnrolments, Collection<Room> rooms) {
        Schedule schedule = new Schedule();
        Collection<Course> scheduledCourses = new HashSet<Course>();
        schedule.setScheduledCourses(scheduledCourses);
        courseEnrolments.forEach(ce -> {
            Room room = rooms.iterator().next();
            ce.getCourse().setRoomId(room.getId());
            scheduledCourses.add(ce.getCourse());
        });
        return schedule;
    }
    
}
