package ru.headsandhands.homeservice.Controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.headsandhands.homeservice.Model.Home;
import ru.headsandhands.homeservice.Model.Room;
import ru.headsandhands.homeservice.Request.HomeRequest;
import ru.headsandhands.homeservice.Request.RoomRequest;
import ru.headsandhands.homeservice.Service.RoomService;

@RestController
@RequestMapping("/api")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    //POST /api/rooms
    @PostMapping("/rooms")
    public ResponseEntity<Room> createHome(@RequestParam Long homeId, @Valid @RequestBody RoomRequest roomRequest){
        Room room = roomService.createRoom(homeId, roomRequest);
        return ResponseEntity.ok(room);
    }

}
