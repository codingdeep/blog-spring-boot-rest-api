package com.blog.services.implementation;

import com.blog.exceptions.ResourceNotFoundException;
import com.blog.models.Category;
import com.blog.models.Post;
import com.blog.payloads.CategoryDto;
import com.blog.payloads.PostApiResponse;
import com.blog.payloads.PostDto;
import com.blog.repositories.CategoryRepository;
import com.blog.repositories.PostRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        post.setPostImage("default.jpg");
        post.setCreatedAt(new Date());

        Post post1 = this.postRepository.save(post);

        return this.modelMapper.map(post1, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto,Long postId, Long categoryId, Long userId) {
        Post post= this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
        post.setCategory(category);
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setPostImage(postDto.getPostImage());
        post = this.postRepository.save(post);
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public void deletePost(Long postId) {
        this.postRepository.deleteById(postId);
    }

    @Override
    public PostApiResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String dir) {
        Pageable p;

        Sort sort = (dir.equalsIgnoreCase("DC")) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        p = PageRequest.of(pageNumber,pageSize, sort);

        Page<Post> pages = this.postRepository.findAll(p);
        List<Post> posts = pages.getContent();
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        PostApiResponse postApiResponse = new PostApiResponse();
        postApiResponse.setData(postDtos);
        postApiResponse.setPageNumber(pages.getNumber());
        postApiResponse.setPageSize(pages.getSize());
        postApiResponse.setTotalRecords(pages.getTotalElements());
        postApiResponse.setTotalPages(pages.getTotalPages());
        postApiResponse.setLastPage(pages.isLast());
        return  postApiResponse;
    }

    @Override
    public PostApiResponse getPostByCategoryId(Long categoryId,Integer pageNumber, Integer pageSize, String sortBy, String dir) {
        Pageable p;
        Sort sort = (dir.equalsIgnoreCase("DC")) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        p = PageRequest.of(pageNumber,pageSize, sort);

        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));

        Page<Post> pages = this.postRepository.findByCategory(p, category);

        List<Post> posts = pages.getContent();


        List<PostDto> postDtos =  posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        PostApiResponse postApiResponse = new PostApiResponse();

        postApiResponse.setData(postDtos);
        postApiResponse.setPageNumber(pages.getNumber());
        postApiResponse.setPageSize(pages.getSize());
        postApiResponse.setTotalRecords(pages.getTotalElements());
        postApiResponse.setTotalPages(pages.getTotalPages());
        postApiResponse.setLastPage(pages.isLast());

        return postApiResponse;

    }

    @Override
    public PostDto getByPostId(Long postId) {
        Post post= this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
        return this.modelMapper.map(post,PostDto.class);

    }

    @Override
    public PostApiResponse getByUserId(Long userId, Integer pageNumber, Integer pageSize, String sortBy, String dir) {
        User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        Pageable p;
        Sort sort = (dir.equalsIgnoreCase("DC")) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        p = PageRequest.of(pageNumber,pageSize, sort);

        Page<Post> pages = this.postRepository.findByUser(p, user);
        List<Post> posts = pages.getContent();
        List<PostDto> postDtos =  posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());


        PostApiResponse postApiResponse = new PostApiResponse();

        postApiResponse.setData(postDtos);
        postApiResponse.setPageNumber(pages.getNumber());
        postApiResponse.setPageSize(pages.getSize());
        postApiResponse.setTotalRecords(pages.getTotalElements());
        postApiResponse.setTotalPages(pages.getTotalPages());
        postApiResponse.setLastPage(pages.isLast());

        return postApiResponse;
    }
    @Override
    public List<PostDto>  searchPost(String keyword){
       List<Post> posts = this.postRepository.findByTitleContaining(keyword);
        return posts.stream().map(post->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }
}
