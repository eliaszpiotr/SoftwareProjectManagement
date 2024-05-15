package com.uep.wap.dto;

import java.util.List;

public class BoardColumnDTO {
    private Long columnId;
    private String title;
    private Long boardId;
    private List<Long> issueIds; // Assuming a column can have multiple issues

    // Getters and Setters

    public Long getColumnId() {
        return columnId;
    }

    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public List<Long> getIssueIds() {
        return issueIds;
    }

    public void setIssueIds(List<Long> issueIds) {
        this.issueIds = issueIds;
    }
}