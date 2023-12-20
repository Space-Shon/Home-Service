package ru.headsandhands.homeservice.Service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ru.headsandhands.homeservice.Model.Home;
import ru.headsandhands.homeservice.Repositories.HomeRepositories;
import ru.headsandhands.homeservice.Repositories.RoomRepositories;
import ru.headsandhands.homeservice.Request.HomeRequest;
import ru.headsandhands.homeservice.Service.Impl.HomeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HomeServiceimplTest {

    @Mock
    private HomeRepositories homeRepositories;

    @Autowired
    private HomeServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new HomeServiceImpl(homeRepositories);
    }

    @Test
    void getHomes() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwic3ViIjoiMTIzNDU2Nzg5MCIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMn0.Bq50di-dDiyIOgTkE2-Svue_Kp7BLbW5hodd57QSY3s";
        String apiUrl = "http://localhost:8082/api/token";
        String responseBody = "{\"id\":1, \"name\":\"Home1\", \"address\":\"Address1\", \"ownerid\":\"1\"}";
        HttpHeaders expectedHeaders = new HttpHeaders();
        expectedHeaders.add("token", token);
        ResponseEntity<String> mockResponse = new ResponseEntity<>(responseBody, HttpStatus.OK);

        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        Mockito.when(restTemplateMock.exchange(apiUrl, HttpMethod.GET, new HttpEntity<>(expectedHeaders), String.class))
                .thenReturn(mockResponse);

        List<Home> expectedHomes = new ArrayList<>();
        Mockito.when(homeRepositories.findByAllHouse(responseBody)).thenReturn(expectedHomes);

        List<Home> actualHomes = service.getHome(token);
        assertEquals(expectedHomes, actualHomes);

    }


    @Test
    void createHome() {

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwic3ViIjoiMTIzNDU2Nzg5MCIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMn0.Bq50di-dDiyIOgTkE2-Svue_Kp7BLbW5hodd57QSY3s";
        String apiUrl = "http://localhost:8082/api/token";
        String responseBody = "{\"id\":1, \"name\":\"Home1\", \"address\":\"Address1\", \"ownerid\":\"1\"}";
        HttpHeaders expectedHeaders = new HttpHeaders();
        expectedHeaders.add("token", token);
        ResponseEntity<String> mockResponse = new ResponseEntity<>(responseBody, HttpStatus.OK);

        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        Mockito.when(restTemplateMock.exchange(apiUrl, HttpMethod.GET, new HttpEntity<>(expectedHeaders), String.class))
                .thenReturn(mockResponse);

        HomeRequest homeRequest = new HomeRequest();
        homeRequest.setName("Home 1");
        homeRequest.setAddress("Nevsky 1");

        Home expectedHome = new Home();
        expectedHome.setId(1);
        expectedHome.setName(homeRequest.getName());
        expectedHome.setAddress(homeRequest.getAddress());
        expectedHome.setOwnerId(responseBody);

        Mockito.when(homeRepositories.save(Mockito.any(Home.class))).thenReturn(expectedHome);

        Home actualHome = service.createHome(token, homeRequest);
        Assertions.assertEquals(expectedHome, actualHome);

    }

    @Test
    void getSingleHome() {
        Integer id = 1;
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwic3ViIjoiMTIzNDU2Nzg5MCIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMn0.Bq50di-dDiyIOgTkE2-Svue_Kp7BLbW5hodd57QSY3s";
        String apiUrl = "http://localhost:8082/api/token";
        String responseBody = "{\"id\":1, \"name\":\"Home1\", \"address\":\"Address1\", \"ownerid\":\"1\"}";
        HttpHeaders expectedHeaders = new HttpHeaders();
        expectedHeaders.add("token", token);
        ResponseEntity<String> mockResponse = new ResponseEntity<>(responseBody, HttpStatus.OK);

        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        Mockito.when(restTemplateMock.exchange(apiUrl, HttpMethod.GET, new HttpEntity<>(expectedHeaders), String.class))
                .thenReturn(mockResponse);

        HomeRequest homeRequest = new HomeRequest();
        homeRequest.setName("Home 1");
        homeRequest.setAddress("Nevsky 1");

        Home expectedHome = new Home();
        expectedHome.setId(id);
        expectedHome.setName(homeRequest.getName());
        expectedHome.setAddress(homeRequest.getAddress());
        expectedHome.setOwnerId(responseBody);
        Mockito.when(homeRepositories.findByHouseId(Mockito.anyInt(), Mockito.anyString())).thenReturn(Optional.of(expectedHome));

        Optional<Home> actualHome = service.getHomeId(token, id);
        Assertions.assertEquals(expectedHome, actualHome.orElse(null));
    }


}