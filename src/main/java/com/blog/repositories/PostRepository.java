package com.blog.repositories;

import com.blog.models.Category;
import com.blog.models.Post;
import com.blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    public List<Post> findByUser(User user);
    public List<Post> findByCategory(Category category);
}
