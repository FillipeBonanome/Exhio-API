package com.exhio_api.exhio_api.dto.quest;

import com.exhio_api.exhio_api.domain.Quest;
import com.exhio_api.exhio_api.dto.hunt.ReadSimpleHuntDTO;

import java.util.Map;

public record ReadQuestDTO(
        Long id,
        String name,
        Integer requiredLevel,
        ReadSimpleHuntDTO hunt,
        Integer moneyReward,
        String itemReward,
        Integer trainingReward,
        Map<String, Integer> attributeRewards
) {
    public ReadQuestDTO(Quest quest) {
        this(
                quest.getId(),
                quest.getName(),
                quest.getRequiredLevel(),
                new ReadSimpleHuntDTO(quest.getHunt()),
                quest.getMoneyReward(),
                quest.getItemReward(),
                quest.getTrainingReward(),
                quest.getAttributeRewards()
                );
    }
}
