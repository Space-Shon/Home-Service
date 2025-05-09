package ru.headsandhands.homeservice.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.headsandhands.homeservice.Model.Room;
import ru.headsandhands.homeservice.Request.RoomRequest;
import ru.headsandhands.homeservice.Service.RoomService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/homes/api/")
public class RoomController {

    private final RoomService roomService;

    //POST /api/rooms
    @PostMapping("/rooms")
    public ResponseEntity<Room> createRoom(@RequestParam Long homeId, @Valid @RequestBody RoomRequest roomRequest) {
        Room room = roomService.createRoom(homeId, roomRequest);
        return ResponseEntity.ok(room);
    }

    //Put /api/rooms/{id}
    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> putHome(@PathVariable Long id, @Valid @RequestBody RoomRequest roomRequest) {
        Room room = roomService.putRoom(id, roomRequest);
        return ResponseEntity.ok(room);
    }

    //Delete /api/rooms/{id}
    @DeleteMapping("/rooms/{id}")
    public void deleteRooms(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}
