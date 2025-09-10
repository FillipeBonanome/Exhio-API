package com.exhio_api.exhio_api.domain;

import com.exhio_api.exhio_api.dto.vocations.CreateVocationDTO;
import com.exhio_api.exhio_api.dto.vocations.UpdateVocationDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "vocations")
public class Vocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "vocations")
    private Set<Hunt> hunts = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "vocations")
    private Set<Spell> spells = new HashSet<>();

    @Embedded
    private Stats stats;

    public Vocation(@Valid CreateVocationDTO vocationDTO) {
        this.name = vocationDTO.name();
        this.description = vocationDTO.description();
        this.stats = vocationDTO.stats();
    }

    public void updateData(@Valid UpdateVocationDTO vocationDTO) {
        if (vocationDTO.name() != null) {
            this.name = vocationDTO.name();
        }
        if (vocationDTO.stats() != null) {
            this.stats = vocationDTO.stats();
        }
        if (vocationDTO.description() != null) {
            this.description = vocationDTO.description();
        }
    }
}
