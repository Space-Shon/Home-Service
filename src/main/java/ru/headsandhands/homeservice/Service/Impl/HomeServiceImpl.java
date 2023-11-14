package ru.headsandhands.homeservice.Service.Impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
    public List<Home> getHome(){
        return homeRepositories.findAll();
    }
    //POST
    @Override
    public Home createHome(HomeRequest homeRequest){
        return homeRepositories.save(Home.builder()
                .name(homeRequest.getName())
                .address(homeRequest.getAddress())
                .build());
    }
    //PUT
    @Override
    public Home putHome(Home home){
        return homeRepositories.save(home);
    }
    //GET ID
    @Override
    public Optional<Home> getHomeId(Integer id){
        return homeRepositories.findById(id);
    }
    //DELETE ID
    @Override
    public void deleteHome(Integer id){
        homeRepositories.deleteById(id);
    }

}
