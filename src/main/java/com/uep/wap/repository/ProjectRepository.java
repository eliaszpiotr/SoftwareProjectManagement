package com.uep.wap.repository;

import com.uep.wap.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {


    Optional<Project> findByName(String name);


    List<Project> findByDescriptionContainingIgnoreCase(String keyword);

    List<Project> findProjectsWithBoards();

    List<Project> findProjectsWithSprints();

    List<Project> findProjectsWithIssues();

    List<Project> findEmptyProjects();

    List<Project> findByIssueStatus(String status);
}
