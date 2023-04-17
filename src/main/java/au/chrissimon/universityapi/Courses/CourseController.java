package au.chrissimon.universityapi.Courses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    @PostMapping("/courses")
    public ResponseEntity<String> includeCourseInCatalog() {
        return ResponseEntity.created(null).body("");
    }
}
