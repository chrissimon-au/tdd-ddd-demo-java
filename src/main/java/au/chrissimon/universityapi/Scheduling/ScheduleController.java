package au.chrissimon.universityapi.Scheduling;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import au.chrissimon.universityapi.Courses.CourseRepository;
import au.chrissimon.universityapi.Rooms.Room;
import au.chrissimon.universityapi.Rooms.RoomRepository;

@RestController
public class ScheduleController {
    private CourseRepository courseRepository;
    private RoomRepository roomRepository;
    private Scheduler scheduler;

    public ScheduleController(CourseRepository courseRepository,
        RoomRepository roomRepository,
        Scheduler scheduler) {
        super();
        this.courseRepository = courseRepository;
        this.roomRepository = roomRepository;
        this.scheduler = scheduler;
    }

    @PostMapping("/schedules")
    public ResponseEntity<Schedule> schedule() {
        
        List<Room> allRooms = roomRepository.findAll();

        List<CourseEnrolments> courseEnrolments = courseRepository.getCoursesWithEnrolments();

        Schedule schedule = scheduler.scheduleCourses(courseEnrolments, allRooms);

        return ResponseEntity.ok().body(schedule);
    }
}
