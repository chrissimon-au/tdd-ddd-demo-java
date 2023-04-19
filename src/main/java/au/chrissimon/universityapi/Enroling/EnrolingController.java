package au.chrissimon.universityapi.Enroling;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import au.chrissimon.universityapi.Students.StudentRepository;

@RestController
public class EnrolingController {
    
    private StudentRepository studentRepository;

    public EnrolingController(StudentRepository studentRepository) {
        super();
        this.studentRepository = studentRepository;
    }

    @PostMapping("/students/{studentId}/courses")
    public ResponseEntity<Enrolment> enrolStudentInCourse(@PathVariable UUID studentId, @RequestBody Enrolment enrolment)
    {
        studentRepository.findById(studentId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.created(null).body(new Enrolment(UUID.randomUUID(), studentId, enrolment.getCourseId()));
    }
}
