package com.programming.techie.springngblogv1.repository;

import com.programming.techie.springngblogv1.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
