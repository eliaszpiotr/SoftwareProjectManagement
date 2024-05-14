package com.uep.wap.service;

import com.uep.wap.model.Board;
import com.uep.wap.model.Project;
import com.uep.wap.repository.BoardRepository;
import com.uep.wap.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ProjectRepository projectRepository;

    // Dodaj nową tablicę do projektu, weryfikując istnienie projektu
    @Transactional
    public Board createBoard(Long projectId, String boardName) throws IllegalArgumentException {
        Optional<Project> project = projectRepository.findById(projectId);
        if (!project.isPresent()) {
            throw new IllegalArgumentException("Project with ID " + projectId + " does not exist");
        }
        Board board = new Board(project.get(), boardName);
        return boardRepository.save(board);
    }

    // Pobierz tablicę po jej ID z walidacją
    public Optional<Board> getBoardById(Long boardId) {
        Optional<Board> board = boardRepository.findById(boardId);
        if (!board.isPresent()) {
            throw new IllegalArgumentException("Board with ID " + boardId + " not found");
        }
        return board;
    }

    // Pobierz wszystkie tablice przypisane do projektu z weryfikacją
    public List<Board> getAllBoardsByProjectId(Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (!project.isPresent()) {
            throw new IllegalArgumentException("Project with ID " + projectId + " does not exist");
        }
        return boardRepository.findByProjectProjectId(projectId);
    }

    // Aktualizacja danych tablicy
    @Transactional
    public Board updateBoard(Board updatedBoard) {
        Optional<Board> existingBoard = boardRepository.findById(updatedBoard.getBoardId());
        if (!existingBoard.isPresent()) {
            throw new IllegalArgumentException("Board with ID " + updatedBoard.getBoardId() + " not found");
        }
        // Można dodać dodatkowe walidacje i logikę biznesową
        existingBoard.get().setName(updatedBoard.getName());
        return boardRepository.save(existingBoard.get());
    }

    // Usuń tablicę z obsługą wyjątków
    @Transactional
    public void deleteBoard(Long boardId) {
        try {
            boardRepository.deleteById(boardId);
        } catch (EmptyResultDataAccessException ex) {
            throw new IllegalArgumentException("Board with ID " + boardId + " not found, unable to delete");
        }
    }
}
