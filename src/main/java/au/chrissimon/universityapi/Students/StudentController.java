package au.chrissimon.universityapi.Students;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class StudentController {
    
    @PostMapping("/students")
    ResponseEntity<Student> registerNewStudent() {
        Student newStudent = Student.register();

        URI newStudentLocation = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(newStudent.getId())
                        .toUri();

        return ResponseEntity.created(newStudentLocation).body(newStudent);
    }
}
