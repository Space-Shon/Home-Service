package ru.headsandhands.homeservice.Controller;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.headsandhands.homeservice.Model.Home;
import ru.headsandhands.homeservice.Request.HomeRequest;
import ru.headsandhands.homeservice.Service.HomeService;

import java.util.List;
import java.util.Optional;

@RestController
@Getter
@Setter
@RequestMapping(value = "/homes/api/", produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {

    private final HomeService homeService;
    public HomeController(HomeService homeService){
        this.homeService = homeService;
    }

    //POST /api/homes
    @PostMapping("/homes")
    public ResponseEntity<Home> createHome(@RequestHeader String token, @RequestBody @Valid HomeRequest request){
        Home home = homeService.createHome(token, request);
        return ResponseEntity.ok(home);
    }

    //PUT /api/homes/{homeId}
    @PutMapping("/homes/{id}")
    public ResponseEntity<Home> putHome(@RequestParam Integer id, @RequestHeader String token, @RequestBody @Valid Home put){
        homeService.putHome(id, token, put);
        return new ResponseEntity<>(put, HttpStatus.OK);
    }

    // GET /api/homes
    @GetMapping("/homes")
    public ResponseEntity<List<Home>> getHome(@RequestHeader String token){
        return new ResponseEntity<>(homeService.getHome(token), HttpStatus.OK);
    }

    //GET /api/homes/{homeId}
    @GetMapping("/homes/{id}")
    public Optional<Home> getHomeId(@RequestHeader String token, @PathVariable Integer id){
        return homeService.getHomeId(token, id);
    }

    //DELETE api/homes/{homeId}
    @DeleteMapping("/homes/{id}")
    public void deleteHome(@RequestHeader String token, @PathVariable Integer id){
        homeService.deleteHome(token, id);
    }
}
