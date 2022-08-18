package com.blog.services.implementation;

import com.blog.exceptions.ResourceNotFoundException;
import com.blog.models.Category;
import com.blog.models.Post;
import com.blog.payloads.PostDto;
import com.blog.repositories.CategoryRepository;
import com.blog.repositories.PostRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import com.blog.models.User;

@Service
public class PostServiceImplementation implements PostService {


    @Resource
    private PostRepository postRepository;

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private UserRepository userRepository;

    @Resource
    private CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto, Long userId, Long categoryId) {

        User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id", userId));

        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);

        post.setUser(user);
        post.setCategory(category);
        post.setCreatedAt(new Date());

        Post post1 = this.postRepository.save(post);

        return this.modelMapper.map(post1, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto) {
        return null;
    }

    @Override
    public void deletePost(Long postId) {

    }

    @Override
    public List<PostDto> getAllPost() {
        return null;
    }

    @Override
    public List<PostDto> getPostByCategoryId(Long categoryId) {
        return null;
    }

    @Override
    public Post getByPostId(Long postId) {
        return null;
    }

    @Override
    public List<PostDto> getByUserId(Long userId) {
        return null;
    }
}
