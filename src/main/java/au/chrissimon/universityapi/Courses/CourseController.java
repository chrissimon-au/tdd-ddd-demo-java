package au.chrissimon.universityapi.Courses;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    @PostMapping("/courses")
    public ResponseEntity<Course> includeCourseInCatalog() {
        return ResponseEntity.created(null).body(new Course(UUID.randomUUID()));
    }
}
