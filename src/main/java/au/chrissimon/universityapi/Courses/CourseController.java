package au.chrissimon.universityapi.Courses;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class CourseController {

    @PostMapping("/courses")
    public ResponseEntity<Course> includeCourseInCatalog() {
        Course newCourse = Course.includeInCatalog();
        return ResponseEntity.created(courseUri(newCourse.getId())).body(newCourse);
    }

    URI courseUri(UUID id) {
        return ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();
    }

}
