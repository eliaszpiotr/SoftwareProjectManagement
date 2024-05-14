package com.uep.wap.repository;

import com.uep.wap.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByProjectProjectId(Long projectId);

    List<Board> findByProjectProjectIdAndName(Long projectId, String name);

    List<Board> findByColumnsIsNotEmpty();

    List<Board> findByColumnsIsEmpty();
}
