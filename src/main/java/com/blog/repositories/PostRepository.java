package com.blog.repositories;

import com.blog.models.Category;
import com.blog.models.Post;
import com.blog.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    public Page findByUser(Pageable p, User user);
    public Page findByCategory(Pageable p, Category category);

    public List<Post> findByTitleContaining(String keyword);
}
