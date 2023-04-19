package au.chrissimon.universityapi.Enroling;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface EnrolmentRepository extends JpaRepository<Enrolment, UUID>, JpaSpecificationExecutor<Enrolment> {
    
}
