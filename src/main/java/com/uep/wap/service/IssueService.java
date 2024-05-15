package com.uep.wap.service;

import com.uep.wap.dto.IssueDTO;
import com.uep.wap.model.Issue;
import com.uep.wap.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    // Convert Issue to IssueDTO
    private IssueDTO convertToDTO(Issue issue) {
        IssueDTO issueDTO = new IssueDTO();
        issueDTO.setIssueId(issue.getIssueId());
        issueDTO.setTitle(issue.getTitle());
        issueDTO.setDescription(issue.getDescription());
        issueDTO.setIssueStatus(issue.getStatus().toString());
        issueDTO.setReporterId(issue.getReportedAt().getUserId);
        issueDTO.setAssigneeId(issue.getReportedAt().getUserId);
        return issueDTO;
    }

    // Convert IssueDTO to Issue
    private Issue convertToEntity(IssueDTO issueDTO) {
        Issue issue = new Issue();
        // Note: You need to fetch the User entities and set them as the reporter and assignee
        // issue.setReporter(reporter);
        // issue.setAssignee(assignee);
        issue.setTitle(issueDTO.getTitle());
        issue.setDescription(issueDTO.getDescription());
        // Note: You need to convert the status string to an IssueStatus enum
        // issue.setStatus(status);
        return issue;
    }

    // Creating a new issue
    @Transactional
    public IssueDTO createIssue(IssueDTO issueDTO) {
        Issue newIssue = convertToEntity(issueDTO);
        return convertToDTO(issueRepository.save(newIssue));
    }

    // Getting an issue by ID
    public IssueDTO getIssueById(Long issueId) {
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new IllegalArgumentException("Issue with ID " + issueId + " not found"));
        return convertToDTO(issue);
    }

    // Deleting an issue
    @Transactional
    public void deleteIssue(Long issueId) {
        if (!issueRepository.existsById(issueId)) {
            throw new IllegalArgumentException("Issue with ID " + issueId + " not found");
        }
        issueRepository.deleteById(issueId);
    }

    // Getting all issues
    public List<IssueDTO> getAllIssues() {
        return issueRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}