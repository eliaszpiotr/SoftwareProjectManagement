package com.uep.wap.controller;

import com.uep.wap.dto.SprintDTO;
import com.uep.wap.service.SprintService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sprints")
public class SprintController {
    private final SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SprintDTO> getSprint(@PathVariable Long id) {
        SprintDTO sprint = sprintService.getSprintById(id);
        return ResponseEntity.ok(sprint);
    }

    @GetMapping
    public ResponseEntity<List<SprintDTO>> getAllSprints() {
        List<SprintDTO> sprints = sprintService.getAllSprints();
        return ResponseEntity.ok(sprints);
    }

    @PostMapping
    public ResponseEntity<SprintDTO> createSprint(@RequestBody SprintDTO sprint) {
        SprintDTO createdSprint = sprintService.createSprint(sprint);
        return ResponseEntity.ok(createdSprint);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SprintDTO> updateSprint(@PathVariable Long id, @RequestBody SprintDTO sprint) {
        SprintDTO updatedSprint = sprintService.updateSprint(id, sprint);
        return ResponseEntity.ok(updatedSprint);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSprint(@PathVariable Long id) {
        sprintService.deleteSprint(id);
        return ResponseEntity.ok().build();
    }
}