package com.uep.wap.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    // ManyToOne relationship with Project
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false, length = 100)
    private String name;

    // Constructors
    public Board() {
    }

    public Board(Project project, String name) {
        this.project = project;
        this.name = name;
    }

    // Getters and setters
    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
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

    // Equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board board)) return false;
        return Objects.equals(getBoardId(), board.getBoardId()) && Objects.equals(getProject(), board.getProject()) && Objects.equals(getName(), board.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBoardId(), getProject(), getName());
    }

    // toString

    @Override
    public String toString() {
        return "Board{" +
                "boardId=" + boardId +
                ", project=" + project +
                ", name='" + name + '\'' +
                '}';
    }
}
