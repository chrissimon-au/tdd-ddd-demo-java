package au.chrissimon.universityapi.Courses;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import au.chrissimon.universityapi.Scheduling.CourseEnrolments;

public interface CourseRepository extends JpaRepository<Course, UUID> {
    @Query("SELECT new au.chrissimon.universityapi.Scheduling.CourseEnrolments(c, COUNT(e.id)) FROM Course c INNER JOIN Enrolment e ON c.id = e.courseId GROUP BY c")
    List<CourseEnrolments> getCoursesWithEnrolments();
}
