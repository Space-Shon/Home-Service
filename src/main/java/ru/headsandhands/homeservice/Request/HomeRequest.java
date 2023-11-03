package ru.headsandhands.homeservice.Request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
@Data
public class HomeRequest {

    @NotBlank
    @Length(min = 0, max = 30)
    private String name;

    @Nullable
    @Length(min = 0, max = 50)
    private String address;

}
