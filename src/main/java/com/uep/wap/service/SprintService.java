package com.uep.wap.service;

import com.uep.wap.dto.SprintDTO;
import com.uep.wap.model.Sprint;
import com.uep.wap.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SprintService {

    @Autowired
    private SprintRepository sprintRepository;

    // Convert Sprint to SprintDTO
    private SprintDTO convertToDTO(Sprint sprint) {
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setId(sprint.getSprintId());
        sprintDTO.setName(sprint.getName());
        sprintDTO.setStartDate(sprint.getStartDate());
        sprintDTO.setEndDate(sprint.getEndDate());
        // Add other fields as necessary
        return sprintDTO;
    }

    // Convert SprintDTO to Sprint
    private Sprint convertToEntity(SprintDTO sprintDTO) {
        Sprint sprint = new Sprint();
        sprint.setSprintId(sprintDTO.getId());
        sprint.setName(sprintDTO.getName());
        sprint.setStartDate(sprintDTO.getStartDate());
        sprint.setEndDate(sprintDTO.getEndDate());
        // Add other fields as necessary
        return sprint;
    }

    // Creating a new sprint
    @Transactional
    public SprintDTO createSprint(SprintDTO sprintDTO) {
        Sprint newSprint = convertToEntity(sprintDTO);
        return convertToDTO(sprintRepository.save(newSprint));
    }

    // Getting a sprint by ID
    public SprintDTO getSprintById(Long sprintId) {
        Sprint sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new IllegalArgumentException("Sprint with ID " + sprintId + " not found"));
        return convertToDTO(sprint);
    }

    // Updating sprint data
    @Transactional
    public SprintDTO updateSprint(Long sprintId, SprintDTO sprintDTO) {
        Sprint existingSprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new IllegalArgumentException("Sprint with ID " + sprintId + " not found"));
        existingSprint.setName(sprintDTO.getName());
        existingSprint.setStartDate(sprintDTO.getStartDate());
        existingSprint.setEndDate(sprintDTO.getEndDate());
        // Update other fields as necessary
        return convertToDTO(sprintRepository.save(existingSprint));
    }

    // Deleting a sprint
    @Transactional
    public void deleteSprint(Long sprintId) {
        if (!sprintRepository.existsById(sprintId)) {
            throw new IllegalArgumentException("Sprint with ID " + sprintId + " not found");
        }
        sprintRepository.deleteById(sprintId);
    }

    // Getting all sprints
    public List<SprintDTO> getAllSprints() {
        return sprintRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}