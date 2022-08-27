package com.blog.services.implementation;

import com.blog.exceptions.ResourceExistException;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.models.Category;
import com.blog.payloads.CategoryDto;
import com.blog.repositories.CategoryRepository;
import com.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private ModelMapper modelMapper;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.categoryRepository.getByCategoryTitle(categoryDto.getCategoryTitle());
        if(category != null){
            throw new ResourceExistException("Category","title", category.getCategoryTitle());
        }
        Category mappedCategory = this.modelMapper.map(categoryDto, Category.class);
        category = this.categoryRepository.save(mappedCategory);
        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());;
        Category updatedCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        this.categoryRepository.deleteById(categoryId);
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));

        return this.modelMapper.map(category, CategoryDto.class);

    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(category ->this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }
}
