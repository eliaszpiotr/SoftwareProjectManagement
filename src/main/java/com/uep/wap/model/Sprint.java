package com.uep.wap.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "sprints")
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sprintId;

    // ManyToOne relationship with Project
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false, length = 100)
    private String name;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    // OneToMany relationship with Task
    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    // Constructors
    public Sprint() {
    }

    public Sprint(Project project, String name, Date startDate, Date endDate) {
        this.project = project;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and setters
    public Long getSprintId() {
        return sprintId;
    }

    public void setSprintId(Long sprintId) {
        this.sprintId = sprintId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    // Method for adding a task to the sprint
    public void addTask(Task task) {
        tasks.add(task);
        task.setSprint(this);
    }

    // Method for removing a task from the sprint
    public void removeTask(Task task) {
        tasks.remove(task);
        task.setSprint(null);
    }

    // Equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sprint sprint)) return false;
        return Objects.equals(getSprintId(), sprint.getSprintId()) && Objects.equals(getProject(), sprint.getProject()) && Objects.equals(getName(), sprint.getName()) && Objects.equals(getStartDate(), sprint.getStartDate()) && Objects.equals(getEndDate(), sprint.getEndDate()) && Objects.equals(getTasks(), sprint.getTasks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSprintId(), getProject(), getName(), getStartDate(), getEndDate(), getTasks());
    }

    // toString
    @Override
    public String toString() {
        return "Sprint{" +
                "sprintId=" + sprintId +
                ", project=" + project +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", tasks=" + tasks +
                '}';
    }
}
