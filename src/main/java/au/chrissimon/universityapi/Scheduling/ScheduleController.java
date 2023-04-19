package au.chrissimon.universityapi.Scheduling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {
    
    @PostMapping("/schedules")
    public ResponseEntity<String> schedule() {
        return ResponseEntity.ok().body("");
    }
}
