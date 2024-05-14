package com.uep.wap.service;

import com.uep.wap.model.Project;
import com.uep.wap.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // Tworzenie nowego projektu
    @Transactional
    public Project createProject(String name, String description) {
        Project project = new Project(name, description);
        return projectRepository.save(project);
    }

    // Pobieranie projektu po ID
    public Optional<Project> getProjectById(Long projectId) {
        return projectRepository.findById(projectId);
    }

    // Aktualizacja projektu
    @Transactional
    public Project updateProject(Long projectId, String name, String description) {
        Project existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project with ID " + projectId + " not found"));
        existingProject.setName(name);
        existingProject.setDescription(description);
        return projectRepository.save(existingProject);
    }

    // Usuwanie projektu
    @Transactional
    public void deleteProject(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new IllegalArgumentException("Project with ID " + projectId + " not found");
        }
        projectRepository.deleteById(projectId);
    }

    // Pobieranie wszystkich projektów
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

}
