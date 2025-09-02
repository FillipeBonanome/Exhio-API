package com.exhio_api.exhio_api.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stats {

    @NotNull
    @Max(value = 10)
    @Min(value = 0)
    private Double simplicity;
    @NotNull
    @Max(value = 10)
    @Min(value = 0)
    private Double areaDamage;
    @NotNull
    @Max(value = 10)
    @Min(value = 0)
    private Double singleDamage;
    @NotNull
    @Max(value = 10)
    @Min(value = 0)
    private Double mobility;
    @NotNull
    @Max(value = 10)
    @Min(value = 0)
    private Double survivability;
    @NotNull
    @Max(value = 10)
    @Min(value = 0)
    private Double resistance;
    @NotNull
    @Max(value = 10)
    @Min(value = 0)
    private Double control;
    @NotNull
    @Max(value = 10)
    @Min(value = 0)
    private Double spells;
    @NotNull
    @Max(value = 10)
    @Min(value = 0)
    private Double support;

}
