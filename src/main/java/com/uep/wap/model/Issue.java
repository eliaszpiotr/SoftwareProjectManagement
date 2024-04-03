package com.uep.wap.model;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private IssueStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private IssueType type;

    @Column(name = "reported_by_id", nullable = false)
    private Long reportedById;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reported_at", nullable = false, updatable = false)
    private Date reportedAt;

    // Getters and setters

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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

    public Long getReportedById() {
        return reportedById;
    }

    public void setReportedById(Long reportedById) {
        this.reportedById = reportedById;
    }

    public Date getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(Date reportedAt) {
        this.reportedAt = reportedAt;
    }

    // Konstruktor bezargumentowy jest wymagany przez JPA
    public Issue() {
    }

    // Konstruktor z argumentami dla łatwiejszego tworzenia obiektów
    public Issue(Long projectId, String title, String description, IssueStatus status, IssueType type, Long reportedById) {
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.type = type;
        this.reportedById = reportedById;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "issueId=" + issueId +
                ", projectId=" + projectId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", reportedById=" + reportedById +
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
