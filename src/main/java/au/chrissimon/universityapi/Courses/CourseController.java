package au.chrissimon.universityapi.Courses;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import au.chrissimon.universityapi.Rooms.Room;
import au.chrissimon.universityapi.Rooms.RoomRepository;
import jakarta.validation.Valid;

@RestController
public class CourseController {

    private CourseRepository courseRepository;
    private RoomRepository roomRepository;

    public CourseController(CourseRepository courseRepository, RoomRepository roomRepository) {
        super();
        this.courseRepository = courseRepository;
        this.roomRepository = roomRepository;
    }

    @PostMapping("/courses")
    public ResponseEntity<Course> includeCourseInCatalog(@Valid @RequestBody Course courseRequest) {
        Room room = roomRepository.findById(courseRequest.getRoomId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        Course newCourse = Course.includeInCatalog(courseRequest.getName(), room.getId());
        courseRepository.save(newCourse);
        return ResponseEntity.created(courseUri(newCourse.getId())).body(newCourse);
    }

    URI courseUri(UUID id) {
        return ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();
    }

    @GetMapping("/courses/{id}")
    public Course getCourse(@PathVariable UUID id) {
        Course course = courseRepository.findById(id)
            .orElseThrow();
        return course;
    }
}
