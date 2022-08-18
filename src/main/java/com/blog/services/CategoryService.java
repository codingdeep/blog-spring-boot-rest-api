package com.blog.services;
import com.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    //create
    public CategoryDto createCategory(CategoryDto categoryDto);

    //update
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

    //delete

    public void deleteCategory(Long categoryId);

    //get single
    public CategoryDto getCategory(Long categoryId);

    //get all category
    public List<CategoryDto> getAllCategory();

}
