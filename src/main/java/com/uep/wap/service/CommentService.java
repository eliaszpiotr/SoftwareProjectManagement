package com.uep.wap.service;

import com.uep.wap.model.Comment;
import com.uep.wap.model.Issue;
import com.uep.wap.model.User;
import com.uep.wap.repository.CommentRepository;
import com.uep.wap.repository.IssueRepository;
import com.uep.wap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    // Dodaj komentarz
    @Transactional
    public Comment createComment(Long issueId, Long authorId, String content) {
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new IllegalArgumentException("Issue with ID " + issueId + " not found"));
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + authorId + " not found"));

        Comment comment = new Comment(issue, author, content);
        return commentRepository.save(comment);
    }

    // Pobierz komentarz po ID
    public Optional<Comment> getCommentById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    // Pobierz wszystkie komentarze dla danego zgłoszenia
    public List<Comment> getCommentsByIssueId(Long issueId) {
        return commentRepository.findByIssueIssueId(issueId);
    }

    // Pobierz wszystkie komentarze napisane przez danego użytkownika
    public List<Comment> getCommentsByAuthorId(Long authorId) {
        return commentRepository.findByAuthorUserId(authorId);
    }

    // Aktualizuj komentarz
    @Transactional
    public Comment updateComment(Long commentId, String newContent) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment with ID " + commentId + " not found"));

        existingComment.setContent(newContent);
        return commentRepository.save(existingComment);
    }

    // Usuń komentarz
    @Transactional
    public void deleteComment(Long commentId) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment with ID " + commentId + " not found"));
        commentRepository.delete(existingComment);
    }
}
