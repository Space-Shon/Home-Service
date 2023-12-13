package ru.headsandhands.homeservice.Service.Impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.headsandhands.homeservice.Model.Home;
import ru.headsandhands.homeservice.Repositories.HomeRepositories;
import ru.headsandhands.homeservice.Request.HomeRequest;
import ru.headsandhands.homeservice.Service.HomeService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class HomeServiceImpl implements HomeService {
    private final HomeRepositories homeRepositories;

    //GET ALL
    @Override
    public List<Home> getHome(String token){
        RestTemplate restTemplate = new RestTemplate();
        String ResourceUrl = "http://localhost:8082/api/token";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("token", token);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(ResourceUrl, HttpMethod.GET, entity, String.class);
        return homeRepositories.findByAllHouse(response.getBody());
    }
    //POST
    @Override
    public Home createHome(String token, HomeRequest homeRequest){
        RestTemplate restTemplate = new RestTemplate();
        String ResourceUrl = "http://localhost:8082/api/token";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("token", token);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(ResourceUrl, HttpMethod.GET, entity, String.class);
        return homeRepositories.save(Home.builder()
                .name(homeRequest.getName())
                .address(homeRequest.getAddress())
                .ownerId(response.getBody())
                .build());
    }
    //PUT
    @Override
    public Home putHome(String token, Home home){
        RestTemplate restTemplate = new RestTemplate();
        String ResourceUrl = "http://localhost:8082/api/token";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("token", token);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(ResourceUrl, HttpMethod.GET, entity, String.class);
        return homeRepositories.save(home);
    }
    //GET ID
    @Override
    public Optional<Home> getHomeId(String token, Integer id){
        RestTemplate restTemplate = new RestTemplate();
        String ResourceUrl = "http://localhost:8082/api/token";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("token", token);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(ResourceUrl, HttpMethod.GET, entity, String.class);
        return homeRepositories.findByHouseId(id, response.getBody());
    }
    //DELETE ID
    @Override
    public void deleteHome(Integer id){
        homeRepositories.deleteById(id);
    }

}
