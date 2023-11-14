package ru.headsandhands.homeservice.Service;

import ru.headsandhands.homeservice.Model.Home;
import ru.headsandhands.homeservice.Request.HomeRequest;

import java.util.List;
import java.util.Optional;

public interface HomeService {
    Home createHome(HomeRequest request);
    Home putHome(Home home);
    List<Home> getHome();
    Optional<Home> getHomeId(Integer id);
    void deleteHome(Integer id);
}
