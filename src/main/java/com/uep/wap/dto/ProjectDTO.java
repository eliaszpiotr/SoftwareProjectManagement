package com.uep.wap.dto;

import java.util.List;

public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private List<Long> taskIds;
    private List<Long> sprintIds;
    private List<Long> userIds;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(List<Long> taskIds) {
        this.taskIds = taskIds;
    }

    public List<Long> getSprintIds() {
        return sprintIds;
    }

    public void setSprintIds(List<Long> sprintIds) {
        this.sprintIds = sprintIds;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }
}