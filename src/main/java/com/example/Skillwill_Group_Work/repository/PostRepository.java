package com.example.Skillwill_Group_Work.repository;

import com.example.Skillwill_Group_Work.models.Post;
import com.example.Skillwill_Group_Work.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByUser(User user, Pageable pageable);
}
