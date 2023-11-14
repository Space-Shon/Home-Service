package ru.headsandhands.homeservice.Service.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import ru.headsandhands.homeservice.Model.Home;
import ru.headsandhands.homeservice.Model.Room;
import ru.headsandhands.homeservice.Repositories.RoomRepositories;
import ru.headsandhands.homeservice.Request.RoomRequest;
import ru.headsandhands.homeservice.Service.RoomService;


@Service
public class RoomServiceImpl implements RoomService {


    @PersistenceContext
    private EntityManager entityManager;
    private final RoomRepositories roomRepositories;

    public RoomServiceImpl(RoomRepositories roomRepositories){
        this.roomRepositories = roomRepositories;
    }

    @Override
    public Room createRoom(Long homeId, RoomRequest roomRequest) {
        Home home = entityManager.getReference(Home.class, homeId);
        return roomRepositories.save(Room.builder()
                .home(home)
                .name(roomRequest.getName())
                .build());
    }

    @Override
    public Room putRoom(Long roomId, RoomRequest roomRequest) {
        Room newRoom = roomRepositories.findById(roomId).get();
        newRoom.setName(roomRequest.getName());
        return roomRepositories.save(newRoom);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepositories.deleteById(id);
    }
}
