package com.exhio_api.exhio_api.domain;

import com.exhio_api.exhio_api.dto.hunt.CreateHuntByMonsterIdDTO;
import com.exhio_api.exhio_api.dto.hunt.CreateHuntByMonsterNameDTO;
import com.exhio_api.exhio_api.dto.hunt.UpdateHuntDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hunts")
@Entity
public class Hunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String recommendedLevel;

    @NotBlank
    private String description;

    @ManyToMany
    @JoinTable(
            name = "hunt_monster",
            joinColumns = @JoinColumn(name = "hunt_id"),
            inverseJoinColumns = @JoinColumn(name = "monster_id")
    )
    private Set<Monster> monsters = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "vocation_hunt",
            joinColumns = @JoinColumn(name = "hunt_id"),
            inverseJoinColumns = @JoinColumn(name = "vocation_id")
    )
    Set<Vocation> vocations = new HashSet<>();
    //Quests

    @Column(name = "video_url")
    private String videoURL;
    @NotNull
    private Boolean premium;

    private Boolean deleted;

    public Hunt(@Valid CreateHuntByMonsterIdDTO huntDTO) {
        this.name = huntDTO.name();
        this.recommendedLevel = huntDTO.recommendedLevel();
        this.description = huntDTO.description();
        this.videoURL = huntDTO.videoURL();
        this.premium = huntDTO.premium();
        this.deleted = false;
    }

    public Hunt(@Valid CreateHuntByMonsterNameDTO huntDTO) {
        this.name = huntDTO.name();
        this.recommendedLevel = huntDTO.recommendedLevel();
        this.description = huntDTO.description();
        this.videoURL = huntDTO.videoURL();
        this.premium = huntDTO.premium();
        this.deleted = false;
    }

    public void updateData(@Valid UpdateHuntDTO huntDTO) {
        if(huntDTO.name() != null) {
            this.name = huntDTO.name();
        }
        if(huntDTO.recommendedLevel() != null) {
            this.recommendedLevel = huntDTO.recommendedLevel();
        }
        if(huntDTO.description() != null) {
            this.description = huntDTO.description();
        }
        if(huntDTO.videoURL() != null) {
            this.videoURL = huntDTO.videoURL();
        }
        if(huntDTO.premium() != null) {
            this.premium = huntDTO.premium();
        }
    }
}
