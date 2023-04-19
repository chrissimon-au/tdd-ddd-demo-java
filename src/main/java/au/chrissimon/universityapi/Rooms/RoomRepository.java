package au.chrissimon.universityapi.Rooms;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import au.chrissimon.universityapi.Enroling.Enrolment;

public interface RoomRepository extends JpaRepository<Room, UUID> {
    
    @Query("SELECT r FROM #{#entityName} r INNER JOIN Course c ON r.Id = c.roomId WHERE c.Id = :#{#enrolment.courseId}")
    Optional<Room> findForEnrolment(@Param("enrolment") Enrolment enrolment);
}
