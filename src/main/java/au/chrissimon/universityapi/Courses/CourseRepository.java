package au.chrissimon.universityapi.Courses;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

interface CourseRepository extends JpaRepository<Course, UUID> {

}
