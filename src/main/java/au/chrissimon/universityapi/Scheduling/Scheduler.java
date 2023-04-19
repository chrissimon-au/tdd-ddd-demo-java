package au.chrissimon.universityapi.Scheduling;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import au.chrissimon.universityapi.Courses.Course;
import au.chrissimon.universityapi.Rooms.Room;

public class Scheduler {

    public Schedule scheduleCourses(Collection<CourseEnrolments> courseEnrolments, Collection<Room> rooms) {
        Schedule schedule = new Schedule();
        Collection<Course> scheduledCourses = new HashSet<Course>();
        schedule.setScheduledCourses(scheduledCourses);
        List<CourseEnrolments> courses = new ArrayList<CourseEnrolments>(courseEnrolments);
        Collections.sort(courses);

        List<Room> availableRooms = new ArrayList<Room>(rooms);
        Collections.sort(availableRooms);
        
        courses.forEach(ce -> {
            availableRooms.stream()
                .filter(r -> r.getCapacity() >= ce.getEnrolmentCount())
                .findFirst()
                .ifPresent(r -> {
                    ce.getCourse().setRoomId(r.getId());
                    availableRooms.remove(r);
                });

            scheduledCourses.add(ce.getCourse());
        });
        return schedule;
    }
    
}
