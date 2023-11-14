package ru.headsandhands.homeservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.headsandhands.homeservice.Model.Room;

@Repository
public interface RoomRepositories extends JpaRepository<Room, Long> {

}
