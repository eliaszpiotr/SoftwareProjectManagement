package com.uep.wap.service;

import com.uep.wap.dto.BoardColumnDTO;
import com.uep.wap.model.BoardColumn;
import com.uep.wap.repository.BoardColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardColumnService {

    @Autowired
    private BoardColumnRepository boardColumnRepository;

    // Convert BoardColumn to BoardColumnDTO
    private BoardColumnDTO convertToDTO(BoardColumn boardColumn) {
        BoardColumnDTO boardColumnDTO = new BoardColumnDTO();
        boardColumnDTO.setColumnId(boardColumn.getColumnId());
        boardColumnDTO.setTitle(boardColumn.getName());
        boardColumnDTO.setBoardId(boardColumn.getBoard().getBoardId());
        // Note: You need to fetch the Issue entities and set them
        // boardColumnDTO.setIssueIds(issueIds);
        return boardColumnDTO;
    }

    // Convert BoardColumnDTO to BoardColumn
    private BoardColumn convertToEntity(BoardColumnDTO boardColumnDTO) {
        BoardColumn boardColumn = new BoardColumn();
        boardColumn.setName(boardColumnDTO.getTitle());
        // Note: You need to convert the issueIds to a list of Issue entities
        // boardColumn.setIssues(issues);
        return boardColumn;
    }

    // Creating a new board column
    @Transactional
    public BoardColumnDTO createBoardColumn(BoardColumnDTO boardColumnDTO) {
        BoardColumn newBoardColumn = convertToEntity(boardColumnDTO);
        return convertToDTO(boardColumnRepository.save(newBoardColumn));
    }

    // Getting a board column by ID
    public BoardColumnDTO getBoardColumnById(Long columnId) {
        BoardColumn boardColumn = boardColumnRepository.findById(columnId)
                .orElseThrow(() -> new IllegalArgumentException("Board Column with ID " + columnId + " not found"));
        return convertToDTO(boardColumn);
    }

    // Deleting a board column
    @Transactional
    public void deleteBoardColumn(Long columnId) {
        if (!boardColumnRepository.existsById(columnId)) {
            throw new IllegalArgumentException("Board Column with ID " + columnId + " not found");
        }
        boardColumnRepository.deleteById(columnId);
    }

    // Getting all board columns
    public List<BoardColumnDTO> getAllBoardColumns() {
        return boardColumnRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}