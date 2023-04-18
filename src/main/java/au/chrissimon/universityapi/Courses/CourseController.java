package au.chrissimon.universityapi.Courses;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class CourseController {

    private CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        super();
        this.courseRepository = courseRepository;
    }

    @PostMapping("/courses")
    public ResponseEntity<Course> includeCourseInCatalog(@RequestBody Course courseRequest) {
        Course newCourse = Course.includeInCatalog(courseRequest.getName());
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
