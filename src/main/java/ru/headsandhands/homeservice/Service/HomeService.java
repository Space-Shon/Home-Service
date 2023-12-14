package ru.headsandhands.homeservice.Service;

import ru.headsandhands.homeservice.Model.Home;
import ru.headsandhands.homeservice.Request.HomeRequest;

import java.util.List;
import java.util.Optional;

public interface HomeService {
    Home createHome(String token, HomeRequest request);
    void putHome(Integer id, String token, Home home);
    List<Home> getHome(String token);
    Optional<Home> getHomeId(String token, Integer id);
    void deleteHome(String token, Integer id);
}
