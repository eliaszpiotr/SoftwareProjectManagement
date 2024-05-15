package com.uep.wap.service;

import com.uep.wap.dto.CommentDTO;
import com.uep.wap.model.Comment;
import com.uep.wap.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // Convert Comment to CommentDTO
    private CommentDTO convertToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentId(comment.getCommentId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setUserId(comment.getAuthor().getUserId());
        commentDTO.setIssueId(comment.getIssue().getIssueId());
        return commentDTO;
    }

    // Convert CommentDTO to Comment
    private Comment convertToEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        // Note: You need to fetch the User and Issue entities and set them
        // comment.setUser(user);
        // comment.setIssue(issue);
        comment.setContent(commentDTO.getContent());
        return comment;
    }

    // Creating a new comment
    @Transactional
    public CommentDTO createComment(CommentDTO commentDTO) {
        Comment newComment = convertToEntity(commentDTO);
        return convertToDTO(commentRepository.save(newComment));
    }

    // Getting a comment by ID
    public CommentDTO getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment with ID " + commentId + " not found"));
        return convertToDTO(comment);
    }

    // Deleting a comment
    @Transactional
    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new IllegalArgumentException("Comment with ID " + commentId + " not found");
        }
        commentRepository.deleteById(commentId);
    }

    // Getting all comments
    public List<CommentDTO> getAllComments() {
        return commentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}