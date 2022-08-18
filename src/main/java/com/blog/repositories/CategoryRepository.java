package com.blog.repositories;
import com.blog.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>  {

   public Category getByCategoryTitle(String categoryTitle);
}
