package au.chrissimon.universityapi.Students;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    
    @PostMapping("/students")
    ResponseEntity<String> registerNewStudent() {
        return ResponseEntity.created(null).body("");
    }
}
