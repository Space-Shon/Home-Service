package ru.headsandhands.homeservice.Service;

import ru.headsandhands.homeservice.Model.HomeEntity;
import ru.headsandhands.homeservice.Request.HomeRequest;

import java.util.List;
import java.util.Optional;

public interface HomeService {
    HomeEntity createHome(String token, HomeRequest request);

    Optional<HomeEntity> getHomeBy(String userId);

    void putHome(Integer id, String token, HomeEntity home);

    List<HomeEntity> getHome(String token);

    Optional<HomeEntity> getHomeId(String token, Integer id);

    void deleteHome(String token, Integer id);
}
