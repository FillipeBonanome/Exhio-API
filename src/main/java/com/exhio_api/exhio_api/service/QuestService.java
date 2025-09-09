package com.exhio_api.exhio_api.service;

import com.exhio_api.exhio_api.domain.Hunt;
import com.exhio_api.exhio_api.domain.Quest;
import com.exhio_api.exhio_api.dto.quest.CreateQuestDTO;
import com.exhio_api.exhio_api.dto.quest.ReadQuestDTO;
import com.exhio_api.exhio_api.repository.HuntRepository;
import com.exhio_api.exhio_api.repository.QuestRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class QuestService {

    @Autowired
    private QuestRepository questRepository;

    @Autowired
    private HuntRepository huntRepository;

    public ReadQuestDTO getById(Long id) {
        return new ReadQuestDTO(questRepository.getReferenceById(id));
    }

    public ReadQuestDTO registerQuest(@Valid CreateQuestDTO questDTO) {
        Hunt hunt = huntRepository.findById(questDTO.huntId()).orElseThrow(() -> new EntityNotFoundException("Hunt not found for quest"));
        //Change exception
        if(questDTO.moneyReward() == null && questDTO.trainingReward() == null && questDTO.itemReward() == null) {
            if(questDTO.attributeRewards() == null || questDTO.attributeRewards().isEmpty()) {
                throw new EntityNotFoundException("A quest always have some reward");
            }
        }

        Quest quest = new Quest(questDTO);
        quest.setHunt(hunt);

        Quest savedQuest = questRepository.save(quest);
        return new ReadQuestDTO(savedQuest);
    }

    public Page<ReadQuestDTO> getQuests(Pageable pageable) {
        Page<Quest> questPage = questRepository.findAll(pageable);
        return questPage.map(ReadQuestDTO::new);
    }

    public void deleteQuestById(Long id) {
        questRepository.deleteById(id);
    }
}
