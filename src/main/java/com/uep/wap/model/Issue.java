package com.uep.wap.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;

    // ManyToOne relationship with Project
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IssueStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IssueType type;

    // OneToMany relationship with Comment
    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date reportedAt;

    // Constructors
    public Issue() {
        reportedAt = new Date(); // Initialize with current date/time
    }

    public Issue(Project project, String title, String description, IssueStatus status, IssueType type) {
        this.project = project;
        this.title = title;
        this.description = description;
        this.status = status;
        this.type = type;
        this.reportedAt = new Date(); // Initialize with current date/time
    }

    // Getters and setters
    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public IssueType getType() {
        return type;
    }

    public void setType(IssueType type) {
        this.type = type;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Date getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(Date reportedAt) {
        this.reportedAt = reportedAt;
    }

    // Equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Issue issue)) return false;
        return Objects.equals(getIssueId(), issue.getIssueId()) && Objects.equals(getProject(), issue.getProject()) && Objects.equals(getTitle(), issue.getTitle()) && Objects.equals(getDescription(), issue.getDescription()) && getStatus() == issue.getStatus() && getType() == issue.getType() && Objects.equals(getComments(), issue.getComments()) && Objects.equals(getReportedAt(), issue.getReportedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIssueId(), getProject(), getTitle(), getDescription(), getStatus(), getType(), getComments(), getReportedAt());
    }

    // toString

    @Override
    public String toString() {
        return "Issue{" +
                "issueId=" + issueId +
                ", project=" + project +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", comments=" + comments +
                ", reportedAt=" + reportedAt +
                '}';
    }
}

enum IssueStatus {
    OPEN, IN_PROGRESS, RESOLVED, CLOSED
}

enum IssueType {
    BUG, FEATURE_REQUEST, DOCUMENTATION
}
