package com.uep.wap.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    // ManyToOne relationship with Sprint
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;

    // ManyToOne relationship with BoardColumn for task's column placement
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "column_id")
    private BoardColumn boardColumn;

    // ManyToOne relationship with User for the assignee
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assignee;

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

    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;

    // Constructors
    public Task() {
        // Default constructor for JPA
    }

    public Task(Sprint sprint, BoardColumn boardColumn, User assignee, String title, String description, TaskStatus status, TaskPriority priority, Date dueDate) {
        this.sprint = sprint;
        this.boardColumn = boardColumn;
        this.assignee = assignee;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    // Getters and Setters
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

    public BoardColumn getBoardColumn() {
        return boardColumn;
    }

    public void setBoardColumn(BoardColumn boardColumn) {
        this.boardColumn = boardColumn;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    // Equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return Objects.equals(getTaskId(), task.getTaskId()) && Objects.equals(getSprint(), task.getSprint()) && Objects.equals(getBoardColumn(), task.getBoardColumn()) && Objects.equals(getAssignee(), task.getAssignee()) && Objects.equals(getTitle(), task.getTitle()) && Objects.equals(getDescription(), task.getDescription()) && getStatus() == task.getStatus() && getPriority() == task.getPriority() && Objects.equals(getDueDate(), task.getDueDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskId(), getSprint(), getBoardColumn(), getAssignee(), getTitle(), getDescription(), getStatus(), getPriority(), getDueDate());
    }

    // toString

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", sprint=" + sprint +
                ", boardColumn=" + boardColumn +
                ", assignee=" + assignee +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                '}';
    }

    public enum TaskPriority {
        LOW, MEDIUM, HIGH, CRITICAL
        // Additional priority values can be added here
    }

    public enum TaskStatus {
        TODO, IN_PROGRESS, DONE, BLOCKED
        // Additional status values can be added here
    }
}

