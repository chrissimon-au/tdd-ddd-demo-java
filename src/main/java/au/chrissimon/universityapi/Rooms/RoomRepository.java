package au.chrissimon.universityapi.Rooms;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

interface RoomRepository extends JpaRepository<Room, UUID> {
    
}
