package ru.headsandhands.homeservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.headsandhands.homeservice.Home.Home;

@Repository
public interface  HomeRepositories extends JpaRepository<Home, Integer> {

}
