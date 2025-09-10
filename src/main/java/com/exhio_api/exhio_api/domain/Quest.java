package com.exhio_api.exhio_api.domain;

import com.exhio_api.exhio_api.dto.quest.CreateQuestDTO;
import com.exhio_api.exhio_api.dto.quest.UpdateQuestDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "quests")
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Integer requiredLevel;

    @ManyToOne
    @JoinColumn(name = "hunt_id")
    private Hunt hunt;

    private Integer moneyReward;
    private String itemReward;
    private Integer trainingReward;

    @ElementCollection
    @CollectionTable(name = "quest_attribute_reward", joinColumns = @JoinColumn(name = "quest_id"))
    @MapKeyColumn(name = "attribute_name")
    @Column(name = "reward_points")
    private Map<String, Integer> attributeRewards = new HashMap<>();

    public Quest(@Valid CreateQuestDTO questDTO) {
        this.name = questDTO.name();
        this.requiredLevel = questDTO.requiredLevel();
        this.moneyReward = questDTO.moneyReward();
        this.itemReward = questDTO.itemReward();
        this.trainingReward = questDTO.trainingReward();
        this.attributeRewards = questDTO.attributeRewards();
    }

    public void update(UpdateQuestDTO questDTO) {
        if(questDTO.name() != null && !questDTO.name().isBlank()) {
            this.name = questDTO.name();
        }
        if(questDTO.requiredLevel() != null) {
            this.requiredLevel = questDTO.requiredLevel();
        }
        if(questDTO.moneyReward() != null) {
            this.moneyReward = questDTO.moneyReward();
        }
        if(questDTO.itemReward() != null && !questDTO.itemReward().isBlank()) {
            this.itemReward = questDTO.itemReward();
        }
        if(questDTO.trainingReward() != null) {
            this.trainingReward = questDTO.trainingReward();
        }
        if(questDTO.attributeRewards() != null) {
            this.attributeRewards = questDTO.attributeRewards();
        }
    }
}
