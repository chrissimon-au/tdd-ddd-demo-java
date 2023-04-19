package au.chrissimon.universityapi.Enroling;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import au.chrissimon.universityapi.Courses.Course;
import au.chrissimon.universityapi.Courses.CourseRepository;

@Component
public class Enroler {

    private CourseRepository courseRepository;

    public Enroler(CourseRepository courseRepository) {
        super();
        this.courseRepository = courseRepository;
    }

    private Function<Course, Optional<Enrolment>> enrol(UUID studentId, UUID courseId) {
        return room -> Optional.of(new Enrolment(UUID.randomUUID(), studentId, courseId));
    }

    public Optional<Enrolment> enrol(UUID studentId, Enrolment enrolment) {
        return courseRepository.findById(enrolment.getCourseId())
            .flatMap(enrol(studentId, enrolment.getCourseId()));
    }
}