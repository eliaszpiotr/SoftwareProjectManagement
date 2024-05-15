package com.uep.wap.service;

import com.uep.wap.dto.ProjectDTO;
import com.uep.wap.model.Project;
import com.uep.wap.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // Convert Project to ProjectDTO
    private ProjectDTO convertToDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getProjectId());
        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());
        // Add other fields as necessary
        return projectDTO;
    }

    // Convert ProjectDTO to Project
    private Project convertToEntity(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setProjectId(projectDTO.getId());
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        // Add other fields as necessary
        return project;
    }

    // Creating a new project
    @Transactional
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project newProject = convertToEntity(projectDTO);
        return convertToDTO(projectRepository.save(newProject));
    }

    // Getting a project by ID
    public ProjectDTO getProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project with ID " + projectId + " not found"));
        return convertToDTO(project);
    }

    // Updating project data
    @Transactional
    public ProjectDTO updateProject(Long projectId, ProjectDTO projectDTO) {
        Project existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project with ID " + projectId + " not found"));
        existingProject.setName(projectDTO.getName());
        existingProject.setDescription(projectDTO.getDescription());
        // Update other fields as necessary
        return convertToDTO(projectRepository.save(existingProject));
    }

    // Deleting a project
    @Transactional
    public void deleteProject(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new IllegalArgumentException("Project with ID " + projectId + " not found");
        }
        projectRepository.deleteById(projectId);
    }

    // Getting all projects
    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}