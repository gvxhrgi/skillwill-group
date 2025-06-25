package com.example.Skillwill_Group_Work.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Skillwill_Group_Work.models.Comment;
import com.example.Skillwill_Group_Work.models.Post;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
