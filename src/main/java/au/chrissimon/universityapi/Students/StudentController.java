package au.chrissimon.universityapi.Students;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    
    @PostMapping("/students")
    ResponseEntity<Student> registerNewStudent() {
        Student newStudent = Student.register();
        return ResponseEntity.created(null).body(newStudent);
    }
}
