package com.uep.wap.model;

import javax.persistence.*;

@Entity
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false, length = 100)
    private String name;

    // Konstruktor bezargumentowy jest wymagany przez JPA
    public Board() {
    }

    // Konstruktor z argumentami dla łatwiejszego tworzenia obiektów
    public Board(Project project, String name) {
        this.project = project;
        this.name = name;
    }

    // Gettery i settery
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


}