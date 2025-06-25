package com.example.Skillwill_Group_Work.service;

import com.example.Skillwill_Group_Work.models.Post;
import com.example.Skillwill_Group_Work.models.User;
import com.example.Skillwill_Group_Work.repository.PostRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;

    public Post createPost(Post post, String username) {
        User user = userService.validateUsername(username);
        post.setUser(user);
        return postRepository.save(post);
    }

    public void deletePost(Long postId, String username) {
        User user = userService.validateUsername(username);
        Optional<Post> postOpt = postRepository.findById(postId);
        Post post;
        if (postOpt.isPresent()) {
            post = postOpt.get();
        } else {
            throw new RuntimeException("Post not found");
        }
        if (!post.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        postRepository.delete(post);
    }

    public Post updatePostText(Long postId, String username, String newText) {
        User user = userService.validateUsername(username);
        Optional<Post> postOpt = postRepository.findById(postId);
        Post post;
        if (postOpt.isPresent()) {
            post = postOpt.get();
        } else {
            throw new RuntimeException("Post not found");
        }
        if (!post.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        post.setText(newText);
        return postRepository.save(post);
    }

    public Post getPost(Long postId, String username) {
        userService.validateUsername(username);
        Optional<Post> postOpt = postRepository.findById(postId);
        if (postOpt.isPresent()) {
            return postOpt.get();
        } else {
            throw new RuntimeException("Post not found");
        }
    }

    public Page<Post> getPostsByUser(String username, int page, int size) {
        User user = userService.validateUsername(username);
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findByUser(user, pageable);
    }

    public Page<Post> getAllPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findAll(pageable);
    }
}
