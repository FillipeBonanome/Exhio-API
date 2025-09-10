package com.exhio_api.exhio_api.domain;

import com.exhio_api.exhio_api.dto.spell.CreateSpellDTO;
import com.exhio_api.exhio_api.dto.spell.UpdateSpellDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "spells")
public class Spell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "spell_vocation",
            joinColumns = @JoinColumn(name = "spell_id"),
            inverseJoinColumns = @JoinColumn(name = "vocation_id")
    )
    Set<Vocation> vocations = new HashSet<>();

    @Column(name = "video_url")
    private String videoURL;

    @ElementCollection
    @CollectionTable(name = "spell_attributes", joinColumns = @JoinColumn(name = "spell_id"))
    @MapKeyColumn(name = "attribute_name")
    @Column(name = "amount")
    private Map<String, Integer> attributes = new HashMap<>();

    public Spell(@Valid CreateSpellDTO spellDTO) {
        this.name = spellDTO.name();
        this.description = spellDTO.description();
        this.videoURL = spellDTO.videoURL();
        this.attributes = spellDTO.attributes();
    }

    public void update(UpdateSpellDTO spellDTO) {
        if(spellDTO.name() != null && !spellDTO.name().isBlank()) {
            this.name = spellDTO.name();
        }
        if(spellDTO.description() != null) {
            this.description = spellDTO.description();
        }
        if(spellDTO.videoURL() != null) {
            this.videoURL = spellDTO.videoURL();
        }
        if(spellDTO.attributes() != null && !spellDTO.attributes().isEmpty()) {
            this.attributes = spellDTO.attributes();
        }
    }
}
