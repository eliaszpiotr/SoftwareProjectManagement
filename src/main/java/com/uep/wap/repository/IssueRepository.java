package com.uep.wap.repository;

import com.uep.wap.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    List<Issue> findByProjectProjectId(Long projectId);

    List<Issue> findByProjectProjectIdAndStatus(Long projectId, String status);

    List<Issue> findByProjectProjectIdAndType(Long projectId, String type);

    List<Issue> findByReportedAtBefore(Date date);

    List<Issue> findByReportedAtAfter(Date date);

    List<Issue> findByStatus(Issue.IssueStatus status);

    List<Issue> findByType(Issue.IssueType type);
}
