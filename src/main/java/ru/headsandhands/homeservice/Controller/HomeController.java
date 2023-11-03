package ru.headsandhands.homeservice.Controller;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.headsandhands.homeservice.Home.Home;
import ru.headsandhands.homeservice.Request.HomeRequest;
import ru.headsandhands.homeservice.Service.HomeService;

import java.util.List;
import java.util.Optional;

@RestController
@Getter
@Setter
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {

    private final HomeService homeService;
    public HomeController(HomeService homeService){
        this.homeService = homeService;
    }

    //POST /api/homes
    @PostMapping("/homes")
    public ResponseEntity<Home> createHome(@RequestBody @Valid HomeRequest request){
        Home home = homeService.createHome(request);
        return ResponseEntity.ok(home);
    }

    //PUT /api/homes/{homeId}
    @PutMapping("/homes/{id}")
    public ResponseEntity<Home> putHome(@RequestBody @Valid Home put){
        Home home = homeService.putHome(put);
        return ResponseEntity.ok(home);
    }

    // GET /api/homes
    @GetMapping("/homes")
    public ResponseEntity<List<Home>> getHome(){
        return new ResponseEntity<>(homeService.getHome(), HttpStatus.OK);
    }

    //GET /api/homes/{homeId}
    @GetMapping("/homes/{id}")
    public Optional<Home> getHomeId(@PathVariable Integer id){
        return homeService.getHomeId(id);
    }

    //DELETE api/homes/{homeId}
    @DeleteMapping("/homes/{id}")
    public void deleteHome(@PathVariable Integer id){
        homeService.deleteHome(id);
    }
}
