package com.uep.wap.service;

import com.uep.wap.dto.BoardDTO;
import com.uep.wap.model.Board;
import com.uep.wap.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // Convert Board to BoardDTO
    private BoardDTO convertToDTO(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardId(board.getBoardId());
        boardDTO.setTitle(board.getTitle());
        boardDTO.setDescription(board.get());
        // Note: You need to fetch the Issue entities and set them
        // boardDTO.setIssueIds(issueIds);
        return boardDTO;
    }

    // Convert BoardDTO to Board
    private Board convertToEntity(BoardDTO boardDTO) {
        Board board = new Board();
        board.setTitle(boardDTO.getTitle());
        board.setDescription(boardDTO.getDescription());
        // Note: You need to convert the issueIds to a list of Issue entities
        // board.setIssues(issues);
        return board;
    }

    // Creating a new board
    @Transactional
    public BoardDTO createBoard(BoardDTO boardDTO) {
        Board newBoard = convertToEntity(boardDTO);
        return convertToDTO(boardRepository.save(newBoard));
    }

    // Getting a board by ID
    public BoardDTO getBoardById(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board with ID " + boardId + " not found"));
        return convertToDTO(board);
    }

    // Deleting a board
    @Transactional
    public void deleteBoard(Long boardId) {
        if (!boardRepository.existsById(boardId)) {
            throw new IllegalArgumentException("Board with ID " + boardId + " not found");
        }
        boardRepository.deleteById(boardId);
    }

    // Getting all boards
    public List<BoardDTO> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}