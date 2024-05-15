package com.uep.wap.dto;

import java.util.List;

public class BoardDTO {
    private Long boardId;
    private String title;
    private String description;
    private List<Long> issueIds; // Assuming a board can have multiple issues

    // Getters and Setters

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
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

    public List<Long> getIssueIds() {
        return issueIds;
    }

    public void setIssueIds(List<Long> issueIds) {
        this.issueIds = issueIds;
    }
}