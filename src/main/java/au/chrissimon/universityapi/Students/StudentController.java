package au.chrissimon.universityapi.Students;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class StudentController {
    
    @PostMapping("/students")
    ResponseEntity<Student> registerNewStudent(@RequestBody Student registerStudentRequest) {
        Student newStudent = Student.register(registerStudentRequest.getName());

        URI newStudentLocation = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(newStudent.getId())
                        .toUri();

        return ResponseEntity.created(newStudentLocation).body(newStudent);
    }

    @GetMapping("/students/{id}")
    void getStudent() {

    }
    
}
