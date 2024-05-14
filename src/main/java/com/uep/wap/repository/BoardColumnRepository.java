package com.uep.wap.repository;

import com.uep.wap.model.Board;
import com.uep.wap.model.BoardColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardColumnRepository extends JpaRepository<BoardColumn, Long> {

    List<BoardColumn> findByBoardBoardId(Long boardId);

    List<BoardColumn> findByBoardBoardIdAndName(Long boardId, String name);

    List<BoardColumn> findByTasksNotEmpty();

    List<BoardColumn> findByTasksEmpty();
}
