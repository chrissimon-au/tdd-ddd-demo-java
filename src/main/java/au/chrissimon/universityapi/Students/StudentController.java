package au.chrissimon.universityapi.Students;

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

@RestController
public class StudentController {
    
    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        super();
        this.studentRepository = studentRepository;
    }

    @PostMapping("/students")
    ResponseEntity<Student> registerNewStudent(@RequestBody Student registerStudentRequest) {
        Student newStudent = Student.register(registerStudentRequest.getName());

        this.studentRepository.save(newStudent);

        URI newStudentLocation = studentUri(newStudent.getId());

        return ResponseEntity
                .created(newStudentLocation)
                .body(newStudent);
    }

    URI studentUri(UUID id) {
        return ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();
    }

    @GetMapping("/students/{id}")
    Student getStudent(@PathVariable UUID id) {
        Student student = this.studentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return student;
    }
    
}
