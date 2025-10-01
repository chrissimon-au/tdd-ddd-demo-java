package au.chrissimon.universityapi.Scheduling;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import au.chrissimon.universityapi.Courses.Course;
import au.chrissimon.universityapi.Rooms.Room;

@Component
public class Scheduler {

    private Optional<Room> smallestAvailableRoom(List<Room> availableRooms, Long requiredCapacity) {
        return availableRooms.stream()
            .filter(r -> r.getCapacity() >= requiredCapacity)
            .findFirst();
    }

    private Optional<Course> assignCourseToRoom(CourseEnrolments courseEnrolment, List<Room> availableRooms) {
        return smallestAvailableRoom(availableRooms, courseEnrolment.getEnrolmentCount())
                .map(r -> {
                    courseEnrolment.getCourse().setRoomId(r.getId());
                    availableRooms.remove(r);
                    return courseEnrolment.getCourse();
                });
    }

    private Collection<Course> assignCoursesToRooms(List<CourseEnrolments> courses, List<Room> availableRooms) {
        return courses
            .stream()
            .map(ce -> assignCourseToRoom(ce, availableRooms))
            .flatMap(Optional::stream)
            .toList();
    }

    public Schedule scheduleCourses(Collection<CourseEnrolments> courseEnrolments, Collection<Room> rooms) {
        List<CourseEnrolments> coursesByMostPopular = new ArrayList<CourseEnrolments>(courseEnrolments);
        Collections.sort(coursesByMostPopular);

        List<Room> availableRoomsBySmallest = new ArrayList<Room>(rooms);
        Collections.sort(availableRoomsBySmallest);
        
        Collection<Course> scheduledCourses = assignCoursesToRooms(coursesByMostPopular, availableRoomsBySmallest);

        return new Schedule(scheduledCourses);
    }
    
}
