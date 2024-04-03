package com.uep.wap.model;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    // ManyToOne relationship with Issue
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id", nullable = false)
    private Issue issue;

    // ManyToOne relationship with User for the author
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    // Constructors
    public Comment() {
    }

    public Comment(Issue issue, User author, String content) {
        this.issue = issue;
        this.author = author;
        this.content = content;
    }

    // Getters and setters
    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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
    // No setter for createdAt as it's automatically managed

    // Equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment comment)) return false;
        return Objects.equals(getCommentId(), comment.getCommentId()) && Objects.equals(getIssue(), comment.getIssue()) && Objects.equals(getAuthor(), comment.getAuthor()) && Objects.equals(getContent(), comment.getContent()) && Objects.equals(getCreatedAt(), comment.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCommentId(), getIssue(), getAuthor(), getContent(), getCreatedAt());
    }

    // toString

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", issue=" + issue +
                ", author=" + author +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
