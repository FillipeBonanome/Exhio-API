package com.exhio_api.exhio_api.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class Resistances {

    @Max(value = 100, message = "Any resistance should not be greater than 100")
    private Integer physical;
    @Max(value = 100, message = "Any resistance should not be greater than 100")
    private Integer fire;
    @Max(value = 100, message = "Any resistance should not be greater than 100")
    private Integer energy;
    @Max(value = 100, message = "Any resistance should not be greater than 100")
    private Integer ice;
    @Max(value = 100, message = "Any resistance should not be greater than 100")
    private Integer poison;
    @Max(value = 100, message = "Any resistance should not be greater than 100")
    private Integer death;
    @Max(value = 100, message = "Any resistance should not be greater than 100")
    private Integer holy;

}
