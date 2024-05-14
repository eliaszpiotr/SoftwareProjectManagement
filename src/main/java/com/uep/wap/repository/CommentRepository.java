package com.uep.wap.repository;

import com.uep.wap.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByIssueIssueId(Long issueId);

    List<Comment> findByAuthorUserId(Long userId);

    List<Comment> findByIssueIssueIdAndAuthorUserId(Long issueId, Long userId);

    List<Comment> findByCreatedAtBefore(Date date);

    List<Comment> findByCreatedAtAfter(Date date);
}
