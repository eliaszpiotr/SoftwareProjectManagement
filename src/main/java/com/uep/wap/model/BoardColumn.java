package com.uep.wap.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "board_columns")
public class BoardColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long columnId;

    // ManyToOne relationship with Board
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(nullable = false, length = 100)
    private String name;

    // OneToMany relationship with Task
    @OneToMany(mappedBy = "boardColumn", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    // Constructors
    public BoardColumn() {
    }

    public BoardColumn(Board board, String name) {
        this.board = board;
        this.name = name;
    }

    // Getters and setters
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    // Equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardColumn that)) return false;
        return Objects.equals(getColumnId(), that.getColumnId()) && Objects.equals(getBoard(), that.getBoard()) && Objects.equals(getName(), that.getName()) && Objects.equals(getTasks(), that.getTasks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColumnId(), getBoard(), getName(), getTasks());
    }

    // toString

    @Override
    public String toString() {
        return "BoardColumn{" +
                "columnId=" + columnId +
                ", board=" + board +
                ", name='" + name + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}
