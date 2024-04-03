package com.uep.wap.model;

import javax.persistence.*;

@Entity
@Table(name = "board_columns")
public class BoardColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long columnId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(nullable = false, length = 100)
    private String name;

    // Konstruktor bezargumentowy jest wymagany przez JPA
    public BoardColumn() {
    }

    // Konstruktor z argumentami dla łatwiejszego tworzenia obiektów
    public BoardColumn(Board board, String name) {
        this.board = board;
        this.name = name;
    }

    // Gettery i settery
    public Long getColumnId() {
        return columnId;
    }

    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
