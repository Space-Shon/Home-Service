package ru.headsandhands.homeservice.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoomRequest {

    @NotBlank
    private String name;

}
