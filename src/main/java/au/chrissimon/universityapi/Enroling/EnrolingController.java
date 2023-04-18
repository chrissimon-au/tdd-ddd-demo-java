package au.chrissimon.universityapi.Enroling;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnrolingController {
    
    @PostMapping("/students/{studentId}/courses")
    public ResponseEntity<Enrolment> enrolStudentInCourse(@PathVariable UUID studentId)
    {
        return ResponseEntity.created(null).body(new Enrolment(UUID.randomUUID(), studentId));
    }
}
