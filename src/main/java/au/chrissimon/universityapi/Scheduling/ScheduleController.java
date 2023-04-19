package au.chrissimon.universityapi.Scheduling;

import java.util.Set;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import au.chrissimon.universityapi.Courses.Course;
import au.chrissimon.universityapi.Courses.CourseRepository;
import au.chrissimon.universityapi.Rooms.Room;
import au.chrissimon.universityapi.Rooms.RoomRepository;

@RestController
public class ScheduleController {
    private CourseRepository courseRepository;
    private RoomRepository roomRepository;

    public ScheduleController(CourseRepository courseRepository,
        RoomRepository roomRepository) {
        super();
        this.courseRepository = courseRepository;
        this.roomRepository = roomRepository;
    }

    @PostMapping("/schedules")
    public ResponseEntity<Schedule> schedule() {
        Course probeCourse = new Course();
        probeCourse.setName("Scheduling Test Course");
        ExampleMatcher courseMatcher = ExampleMatcher.matching().withIgnorePaths("id", "roomId");
        Course course = courseRepository.findOne(Example.of(probeCourse, courseMatcher)).get();
        
        Room probeRoom = new Room();
        ExampleMatcher roomMatcher = ExampleMatcher.matching().withIgnorePaths("id", "capacity");
        probeRoom.setName("Scheduling Test Room");
        Room room = roomRepository.findOne(Example.of(probeRoom, roomMatcher)).get();

        course.setRoomId(room.getId());

        return ResponseEntity.ok().body(new Schedule(Set.of(course)));
    }
}
