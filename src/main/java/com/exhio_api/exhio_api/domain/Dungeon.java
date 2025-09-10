package com.exhio_api.exhio_api.domain;

import com.exhio_api.exhio_api.dto.dungeon.CreateDungeonDTO;
import com.exhio_api.exhio_api.dto.dungeon.UpdateDungeonDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "dungeons")
public class Dungeon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "dungeon_monster",
            joinColumns = @JoinColumn(name = "dungeon_id"),
            inverseJoinColumns = @JoinColumn(name = "monster_id")
    )
    private Set<Monster> monsters = new HashSet<>();

    @NotNull
    private Integer requiredLevel;

    @ManyToOne
    @JoinColumn(name = "hunt_id")
    private Hunt hunt;

    public Dungeon(CreateDungeonDTO dungeonDTO) {
        this.name = dungeonDTO.name();
        this.requiredLevel = dungeonDTO.requiredLevel();
    }

    public void update(UpdateDungeonDTO dungeonDTO) {
        if (dungeonDTO.name() != null) {
            this.name = dungeonDTO.name();
        }
        if (dungeonDTO.requiredLevel() != null) {
            this.requiredLevel = dungeonDTO.requiredLevel();
        }
    }
}
