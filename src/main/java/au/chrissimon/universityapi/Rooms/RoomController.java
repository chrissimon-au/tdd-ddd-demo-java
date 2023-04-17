package au.chrissimon.universityapi.Rooms;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {

    @PostMapping("/rooms")
    public ResponseEntity<Room> setupNewRoom() {
        return ResponseEntity.created(null).body(Room.setupNew());
    }
}
