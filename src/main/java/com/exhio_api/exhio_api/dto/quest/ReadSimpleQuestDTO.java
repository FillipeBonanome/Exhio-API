package com.exhio_api.exhio_api.dto.quest;

import com.exhio_api.exhio_api.domain.Quest;

import java.util.Map;

public record ReadSimpleQuestDTO(
        String name,
        Integer requiredLevel,
        Integer moneyReward,
        String itemReward,
        Integer trainingReward,
        Map<String, Integer> attributeRewards
) {
    public ReadSimpleQuestDTO(Quest quest) {
        this(
                quest.getName(),
                quest.getRequiredLevel(),
                quest.getMoneyReward(),
                quest.getItemReward(),
                quest.getTrainingReward(),
                quest.getAttributeRewards()
        );
    }
}
