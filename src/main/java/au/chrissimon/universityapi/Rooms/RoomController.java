package au.chrissimon.universityapi.Rooms;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class RoomController {

    private RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        super();
        this.roomRepository = roomRepository;
    }

    @PostMapping("/rooms")
    public ResponseEntity<Room> setupNewRoom(@RequestBody Room roomRequest) {
        Room newRoom = Room.setupNew(roomRequest);

        this.roomRepository.save(newRoom);

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
    Room getRoom(@PathVariable UUID id) {
        Room room = this.roomRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return room;
    }
}
