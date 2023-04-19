package au.chrissimon.universityapi.Enroling;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import au.chrissimon.universityapi.Courses.CourseRepository;
import au.chrissimon.universityapi.Students.StudentRepository;

@RestController
public class EnrolingController {
    
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public EnrolingController(StudentRepository studentRepository, CourseRepository courseRepository) {
        super();
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping("/students/{studentId}/courses")
    public ResponseEntity<Enrolment> enrolStudentInCourse(@PathVariable UUID studentId, @RequestBody Enrolment enrolment)
    {
        studentRepository.findById(studentId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        courseRepository.findById(enrolment.getCourseId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        Enrolment newEnrolment = new Enrolment(UUID.randomUUID(), studentId, enrolment.getCourseId());
        return ResponseEntity.created(enrolmentUri(newEnrolment.getId())).body(newEnrolment);
    }

    URI enrolmentUri(UUID id) {
        return ServletUriComponentsBuilder
            .fromCurrentRequest()
            .replacePath("enrolments/{id}")
            .buildAndExpand(id)
            .toUri();
    }
}
