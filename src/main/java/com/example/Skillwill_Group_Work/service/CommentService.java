package com.example.Skillwill_Group_Work.service;

import com.example.Skillwill_Group_Work.models.Comment;
import com.example.Skillwill_Group_Work.models.Post;
import com.example.Skillwill_Group_Work.models.User;
import com.example.Skillwill_Group_Work.repository.CommentRepository;
import com.example.Skillwill_Group_Work.repository.PostRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;

    public Comment createComment(Comment comment, String username, Long postId) {
        User user = userService.validateUsername(username);
        Optional<Post> postOpt = postRepository.findById(postId);
        Post post;
        if (postOpt.isPresent()) {
            post = postOpt.get();
        } else {
            throw new RuntimeException("Post not found");
        }
        comment.setUser(user);
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    public void deleteComment(Long commentId, String username) {
        User user = userService.validateUsername(username);
        Optional<Comment> commentOpt = commentRepository.findById(commentId);
        Comment comment;
        if (commentOpt.isPresent()) {
            comment = commentOpt.get();
        } else {
            throw new RuntimeException("Comment not found");
        }
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        commentRepository.delete(comment);
    }

    public Comment updateCommentText(Long commentId, String username, String newText) {
        User user = userService.validateUsername(username);
        Optional<Comment> commentOpt = commentRepository.findById(commentId);
        Comment comment;
        if (commentOpt.isPresent()) {
            comment = commentOpt.get();
        } else {
            throw new RuntimeException("Comment not found");
        }
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        comment.setText(newText);
        return commentRepository.save(comment);
    }

    public java.util.List<Comment> getCommentsByPostId(Long postId) {
        Optional<Post> postOpt = postRepository.findById(postId);
        if (postOpt.isPresent()) {
            return commentRepository.findByPost(postOpt.get());
        } else {
            throw new RuntimeException("Post not found");
        }
    }
}
