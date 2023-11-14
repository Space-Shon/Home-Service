package ru.headsandhands.homeservice.Service;

import ru.headsandhands.homeservice.Model.Room;
import ru.headsandhands.homeservice.Request.RoomRequest;


public interface RoomService {
    Room createRoom(Long homeId, RoomRequest roomRequest);
    Room putRoom(Long roomId, RoomRequest roomRequest);
    void deleteRoom(Long id);
}
