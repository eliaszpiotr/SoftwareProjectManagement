package com.uep.wap.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority;

    // This can be mapped to a User entity with ManyToOne if needed
    @Column(name = "assignee_id")
    private Long assigneeId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;

    // Konstruktor bezargumentowy jest wymagany przez JPA
    public Task() {
    }

    // Konstruktor z argumentami dla łatwiejszego tworzenia obiektów
    public Task(Sprint sprint, String title, String description, TaskStatus status,
                TaskPriority priority, Long assigneeId, Date dueDate) {
        this.sprint = sprint;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.assigneeId = assigneeId;
        this.dueDate = dueDate;
    }

    // Gettery i settery
    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}

enum TaskStatus {
    TODO, IN_PROGRESS, DONE, BLOCKED
}

enum TaskPriority {
    LOW, MEDIUM, HIGH, CRITICAL
}
