package com.example.Skillwill_Group_Work.controller;

import com.example.Skillwill_Group_Work.models.Post;
import com.example.Skillwill_Group_Work.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public Post createPost(@RequestBody Post post, @RequestParam String username) {
        return postService.createPost(post, username);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId, @RequestParam String username) {
        postService.deletePost(postId, username);
    }

    @PutMapping("/{postId}")
    public Post updatePostText(@PathVariable Long postId, @RequestParam String username, @RequestParam String text) {
        return postService.updatePostText(postId, username, text);
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Long postId, @RequestParam String username) {
        return postService.getPost(postId, username);
    }

    @GetMapping("/user/{username}")
    public Page<Post> getPostsByUser(@PathVariable String username, @RequestParam int page, @RequestParam int size) {
        return postService.getPostsByUser(username, page, size);
    }

    @GetMapping
    public Page<Post> getAllPosts(@RequestParam int page, @RequestParam int size) {
        return postService.getAllPosts(page, size);
    }
}
