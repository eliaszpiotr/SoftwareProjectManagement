package com.uep.wap.service;

import com.uep.wap.model.Project;
import com.uep.wap.model.Sprint;
import com.uep.wap.model.Task;
import com.uep.wap.repository.ProjectRepository;
import com.uep.wap.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SprintService {

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private ProjectRepository projectRepository;

    // Tworzenie nowego sprintu
    @Transactional
    public Sprint createSprint(Long projectId, String name, Date startDate, Date endDate) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project with ID " + projectId + " not found"));
        Sprint sprint = new Sprint(project, name, startDate, endDate);
        return sprintRepository.save(sprint);
    }

    // Pobieranie sprintu po ID
    public Optional<Sprint> getSprintById(Long sprintId) {
        return sprintRepository.findById(sprintId);
    }

    // Aktualizacja sprintu
    @Transactional
    public Sprint updateSprint(Long sprintId, String name, Date startDate, Date endDate) {
        Sprint existingSprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new IllegalArgumentException("Sprint with ID " + sprintId + " not found"));
        existingSprint.setName(name);
        existingSprint.setStartDate(startDate);
        existingSprint.setEndDate(endDate);
        return sprintRepository.save(existingSprint);
    }

    // Usunięcie sprintu
    @Transactional
    public void deleteSprint(Long sprintId) {
        if (!sprintRepository.existsById(sprintId)) {
            throw new IllegalArgumentException("Sprint with ID " + sprintId + " not found");
        }
        sprintRepository.deleteById(sprintId);
    }

    // Dodawanie zadania do sprintu
    @Transactional
    public Sprint addTaskToSprint(Long sprintId, Task task) {
        Sprint sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new IllegalArgumentException("Sprint with ID " + sprintId + " not found"));
        sprint.addTask(task);
        return sprintRepository.save(sprint);
    }

    // Usunięcie zadania ze sprintu
    @Transactional
    public Sprint removeTaskFromSprint(Long sprintId, Task task) {
        Sprint sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new IllegalArgumentException("Sprint with ID " + sprintId + " not found"));
        sprint.removeTask(task);
        return sprintRepository.save(sprint);
    }

    // Pobieranie wszystkich sprintów dla danego projektu
    public List<Sprint> getSprintsByProjectId(Long projectId) {
        return sprintRepository.findByProjectProjectId(projectId);
    }
}
