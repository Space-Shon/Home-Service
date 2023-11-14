package ru.headsandhands.homeservice.Service.Impl;

import org.springframework.stereotype.Service;
import ru.headsandhands.homeservice.Model.Room;
import ru.headsandhands.homeservice.Repositories.RoomRepositories;
import ru.headsandhands.homeservice.Request.RoomRequest;
import ru.headsandhands.homeservice.Service.RoomService;


@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepositories roomRepositories;

    public RoomServiceImpl(RoomRepositories roomRepositories){
        this.roomRepositories = roomRepositories;
    }

    @Override
    public Room createRoom(Long homeId, RoomRequest roomRequest) {
        return roomRepositories.save(Room.builder()
                .id(homeId)
                .name(roomRequest.getName())
                .build());
    }

    @Override
    public Room putRoom(Long roomId, Room room) {
        Room newRoom = roomRepositories.findById(roomId).get();
        newRoom.setName(room.getName());
        return roomRepositories.save(room);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepositories.deleteById(id);
    }
}
