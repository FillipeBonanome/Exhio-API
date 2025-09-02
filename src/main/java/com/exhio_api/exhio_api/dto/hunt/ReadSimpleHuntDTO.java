package com.exhio_api.exhio_api.dto.hunt;

import com.exhio_api.exhio_api.domain.Hunt;

public record ReadSimpleHuntDTO(
        Long id,
        String name,
        String recommendedLevel,
        String videoURL,
        Boolean premium
) {
    public ReadSimpleHuntDTO(Hunt hunt) {
        this(
                hunt.getId(),
                hunt.getName(),
                hunt.getRecommendedLevel(),
                hunt.getVideoURL(),
                hunt.getPremium()
        );
    }
}
