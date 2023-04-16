package au.chrissimon.universityapi.Students;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    
    @PostMapping("/students")
    ResponseEntity<Student> registerNewStudent() {
        return ResponseEntity.created(null).body(new Student(UUID.randomUUID()));
    }
}
