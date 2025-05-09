package ru.headsandhands.homeservice.Request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

public record HomeRequest(
        @NotBlank
        @Length(min = 0, max = 30)
        String name,

        @Nullable
        @Length(min = 0, max = 50)
        String address
) {
}
