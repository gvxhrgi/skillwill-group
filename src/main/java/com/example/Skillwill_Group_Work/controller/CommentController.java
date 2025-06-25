package com.example.Skillwill_Group_Work.controller;

import com.example.Skillwill_Group_Work.models.Comment;
import com.example.Skillwill_Group_Work.service.CommentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public Comment createComment(@RequestBody Comment comment, @RequestParam String username, @RequestParam Long postId) {
        return commentService.createComment(comment, username, postId);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId, @RequestParam String username) {
        commentService.deleteComment(commentId, username);
    }

    @PutMapping("/{commentId}")
    public Comment updateCommentText(@PathVariable Long commentId, @RequestParam String username, @RequestParam String text) {
        return commentService.updateCommentText(commentId, username, text);
    }

    @GetMapping("/post/{postId}")
    public List<Comment> getCommentsByPost(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }
}
