package au.chrissimon.universityapi.Enroling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnrolingController {
    
    @PostMapping("/students/{id}/courses")
    public ResponseEntity<String> enrolStudentInCourse()
    {
        return ResponseEntity.created(null).body("");
    }
}
