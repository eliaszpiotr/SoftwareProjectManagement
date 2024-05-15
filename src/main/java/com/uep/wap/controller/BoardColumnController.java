package com.uep.wap.controller;

import com.uep.wap.dto.BoardColumnDTO;
import com.uep.wap.service.BoardColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board-columns")
public class BoardColumnController {

    @Autowired
    private BoardColumnService boardColumnService;

    // Creating a new board column
    @PostMapping
    public ResponseEntity<BoardColumnDTO> createBoardColumn(@RequestBody BoardColumnDTO boardColumnDTO) {
        return ResponseEntity.ok(boardColumnService.createBoardColumn(boardColumnDTO));
    }

    // Getting a board column by ID
    @GetMapping("/{id}")
    public ResponseEntity<BoardColumnDTO> getBoardColumnById(@PathVariable Long id) {
        return ResponseEntity.ok(boardColumnService.getBoardColumnById(id));
    }

    // Deleting a board column
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoardColumn(@PathVariable Long id) {
        boardColumnService.deleteBoardColumn(id);
        return ResponseEntity.ok().build();
    }

    // Getting all board columns
    @GetMapping
    public ResponseEntity<List<BoardColumnDTO>> getAllBoardColumns() {
        return ResponseEntity.ok(boardColumnService.getAllBoardColumns());
    }
}