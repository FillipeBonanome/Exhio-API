package com.exhio_api.exhio_api.domain;

import com.exhio_api.exhio_api.dto.monster.CreateMonsterDTO;
import com.exhio_api.exhio_api.dto.monster.UpdateMonsterDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "monsters")
@Entity
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive(message = "Monster level should be higher than 0")
    private Integer level;

    @NotBlank
    private String name;

    @NotNull
    @Positive(message = "Monster experience should be higher than 0")
    private Integer experience;

    @Embedded
    @Valid
    Resistances resistances;

    @ManyToMany(mappedBy = "monsters")
    Set<Hunt> hunts = new HashSet<>();

    private Boolean deleted;

    public Monster(@Valid CreateMonsterDTO monster) {
        this.level = monster.level();
        this.experience = monster.experience();
        this.name = monster.name();
        this.resistances = monster.resistances();
        this.deleted = false;
    }

    public void updateData(UpdateMonsterDTO monsterDTO) {
        if(monsterDTO.experience() != null) {
            this.experience = monsterDTO.experience();
        }
        if(monsterDTO.level() != null) {
            this.level = monsterDTO.level();
        }
        if(monsterDTO.name() != null) {
            this.name = monsterDTO.name();
        }
        //TODO --> Refactor
        if(monsterDTO.resistances() != null) {
            Resistances resistances = monsterDTO.resistances();
            if(resistances.getPhysical() != null) {
                this.resistances.setPhysical(resistances.getPhysical());
            }
            if(resistances.getFire() != null) {
                this.resistances.setFire(resistances.getFire());
            }
            if(resistances.getIce() != null) {
                this.resistances.setIce(resistances.getIce());
            }
            if(resistances.getEnergy() != null) {
                this.resistances.setEnergy(resistances.getEnergy());
            }
            if(resistances.getDeath() != null) {
                this.resistances.setDeath(resistances.getDeath());
            }
            if(resistances.getPoison() != null) {
                this.resistances.setPoison(resistances.getPoison());
            }
            if(resistances.getHoly() != null) {
                this.resistances.setHoly(resistances.getHoly());
            }
        }
    }
}
