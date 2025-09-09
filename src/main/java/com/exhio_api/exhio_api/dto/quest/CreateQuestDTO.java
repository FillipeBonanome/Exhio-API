package com.exhio_api.exhio_api.dto.quest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record CreateQuestDTO(
        @NotBlank
        String name,
        @NotNull
        Integer requiredLevel,
        @NotNull
        Long huntId,
        Integer moneyReward,
        String itemReward,
        Integer trainingReward,
        Map<String, Integer> attributeRewards
) {
}
