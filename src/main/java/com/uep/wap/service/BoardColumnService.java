package com.uep.wap.service;

import com.uep.wap.model.Board;
import com.uep.wap.model.BoardColumn;
import com.uep.wap.model.Task;
import com.uep.wap.repository.BoardColumnRepository;
import com.uep.wap.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BoardColumnService {

    @Autowired
    private BoardColumnRepository boardColumnRepository;

    @Autowired
    private BoardRepository boardRepository;

    // Dodaj nową kolumnę do tablicy, sprawdzając czy tablica istnieje
    @Transactional
    public BoardColumn createBoardColumn(Long boardId, String columnName) {
        Optional<Board> board = boardRepository.findById(boardId);
        if (!board.isPresent()) {
            throw new IllegalArgumentException("Board with ID " + boardId + " does not exist");
        }
        BoardColumn boardColumn = new BoardColumn(board.get(), columnName);
        return boardColumnRepository.save(boardColumn);
    }

    // Pobierz kolumnę po jej ID
    public Optional<BoardColumn> getBoardColumnById(Long columnId) {
        return boardColumnRepository.findById(columnId);
    }

    // Pobierz wszystkie kolumny przypisane do tablicy
    public List<BoardColumn> getAllColumnsByBoardId(Long boardId) {
        return boardColumnRepository.findByBoardBoardId(boardId);
    }

    // Aktualizuj kolumnę
    @Transactional
    public BoardColumn updateBoardColumn(BoardColumn updatedBoardColumn) {
        Optional<BoardColumn> existingColumn = boardColumnRepository.findById(updatedBoardColumn.getColumnId());
        if (!existingColumn.isPresent()) {
            throw new IllegalArgumentException("BoardColumn with ID " + updatedBoardColumn.getColumnId() + " not found");
        }
        existingColumn.get().setName(updatedBoardColumn.getName());
        // Aktualizuj powiązane zadania, jeśli to konieczne
        existingColumn.get().setTasks(updatedBoardColumn.getTasks());
        return boardColumnRepository.save(existingColumn.get());
    }

    // Usuń kolumnę
    @Transactional
    public void deleteBoardColumn(Long columnId) {
        Optional<BoardColumn> column = boardColumnRepository.findById(columnId);
        if (!column.isPresent()) {
            throw new IllegalArgumentException("BoardColumn with ID " + columnId + " not found");
        }
        boardColumnRepository.delete(column.get());
    }

    // Dodaj zadanie do kolumny
    @Transactional
    public BoardColumn addTaskToColumn(Long columnId, Task task) {
        Optional<BoardColumn> column = boardColumnRepository.findById(columnId);
        if (!column.isPresent()) {
            throw new IllegalArgumentException("BoardColumn with ID " + columnId + " not found");
        }
        column.get().getTasks().add(task);
        task.setBoardColumn(column.get());
        return boardColumnRepository.save(column.get());
    }

    // Usuń zadanie z kolumny
    @Transactional
    public BoardColumn removeTaskFromColumn(Long columnId, Task task) {
        Optional<BoardColumn> column = boardColumnRepository.findById(columnId);
        if (!column.isPresent()) {
            throw new IllegalArgumentException("BoardColumn with ID " + columnId + " not found");
        }
        column.get().getTasks().remove(task);
        task.setBoardColumn(null);
        return boardColumnRepository.save(column.get());
    }
}
