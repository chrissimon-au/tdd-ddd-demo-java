package au.chrissimon.universityapi.Rooms;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class RoomController {

    @PostMapping("/rooms")
    public ResponseEntity<Room> setupNewRoom(@RequestBody Room roomRequest) {
        Room newRoom = Room.setupNew(roomRequest);
        return ResponseEntity.created(roomUri(newRoom.getId())).body(newRoom);
    }

    URI roomUri(UUID id) {
        return ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();
    }

    @GetMapping("/rooms/{id}")
    void getRoom() {

    }
}
