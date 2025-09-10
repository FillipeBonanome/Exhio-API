package com.exhio_api.exhio_api.controller;

import com.exhio_api.exhio_api.dto.quest.CreateQuestDTO;
import com.exhio_api.exhio_api.dto.quest.ReadQuestDTO;
import com.exhio_api.exhio_api.dto.quest.UpdateQuestDTO;
import com.exhio_api.exhio_api.service.QuestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quests")
public class QuestController {

    @Autowired
    private QuestService questService;

    @GetMapping
    public ResponseEntity<Page<ReadQuestDTO>> getQuests(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(questService.getQuests(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadQuestDTO> getQuestById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(questService.getById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ReadQuestDTO> registerQuest(@RequestBody @Valid CreateQuestDTO questDTO) {
        return ResponseEntity.ok(questService.registerQuest(questDTO));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ReadQuestDTO> updateQuest(@PathVariable(name = "id")Long id, @RequestBody UpdateQuestDTO questDTO) {
        return ResponseEntity.ok(questService.updateQuest(id, questDTO));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteQuestById(@PathVariable(name = "id") Long id) {
        questService.deleteQuestById(id);
        return ResponseEntity.ok().build();
    }

}
