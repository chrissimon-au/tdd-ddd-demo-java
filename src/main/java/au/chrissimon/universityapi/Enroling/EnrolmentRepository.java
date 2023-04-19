package au.chrissimon.universityapi.Enroling;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

interface EnrolmentRepository extends JpaRepository<Enrolment, UUID> {
    
}
