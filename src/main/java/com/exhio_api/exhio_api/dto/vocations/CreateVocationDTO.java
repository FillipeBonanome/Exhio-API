package com.exhio_api.exhio_api.dto.vocations;

import com.exhio_api.exhio_api.domain.Stats;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CreateVocationDTO(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @Valid
        Stats stats
) {
}
