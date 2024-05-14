package com.uep.wap.service;

import com.uep.wap.model.Issue;
import com.uep.wap.model.Project;
import com.uep.wap.repository.IssueRepository;
import com.uep.wap.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProjectRepository projectRepository;

    // Tworzenie nowego zgłoszenia
    @Transactional
    public Issue createIssue(Long projectId, String title, String description, Issue.IssueStatus status, Issue.IssueType type) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project with ID " + projectId + " not found"));
        Issue newIssue = new Issue(project, title, description, status, type);
        return issueRepository.save(newIssue);
    }

    // Pobranie zgłoszenia po ID
    public Optional<Issue> getIssueById(Long issueId) {
        return issueRepository.findById(issueId);
    }

    // Do sprawdzenia z tym stringiem
    @Transactional
    public Issue updateIssue(Long issueId, String title, String description, Issue.IssueStatus status, Issue.IssueType type) {
        Issue existingIssue = issueRepository.findById(issueId)
                .orElseThrow(() -> new IllegalArgumentException("Issue with ID " + issueId + " not found"));
        existingIssue.setTitle(title);
        existingIssue.setDescription(description);
        existingIssue.setStatus(status);
        existingIssue.setType(type);
        return issueRepository.save(existingIssue);
    }

    // Usunięcie zgłoszenia
    @Transactional
    public void deleteIssue(Long issueId) {
        if (!issueRepository.existsById(issueId)) {
            throw new IllegalArgumentException("Issue with ID " + issueId + " not found");
        }
        issueRepository.deleteById(issueId);
    }

    // Pobranie wszystkich zgłoszeń dla danego projektu
    public List<Issue> getIssuesByProjectId(Long projectId) {
        return issueRepository.findByProjectProjectId(projectId);
    }

    // Pobranie zgłoszeń na podstawie statusu
    public List<Issue> getIssuesByStatus(Issue.IssueStatus status) {
        return issueRepository.findByStatus(status);
    }

    // Pobranie zgłoszeń na podstawie typu
    public List<Issue> getIssuesByType(Issue.IssueType type) {
        return issueRepository.findByType(type);
    }
}
