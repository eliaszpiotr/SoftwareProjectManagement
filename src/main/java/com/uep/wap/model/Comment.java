package com.uep.wap.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "issue_id", nullable = false)
    private Long issueId;

    @Column(name = "author_id", nullable = false)
    private Long authorId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    // Standard constructors, getters, and setters

    public Comment() {
    }

    public Comment(Long issueId, Long authorId, String content) {
        this.issueId = issueId;
        this.authorId = authorId;
        this.content = content;
    }

    // Getters and setters
    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    // `setCreatedAt` is not needed because the value is set automatically by the @CreationTimestamp annotation
}
