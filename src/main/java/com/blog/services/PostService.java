package com.blog.services;

import com.blog.models.Post;
import com.blog.payloads.PostApiResponse;
import com.blog.payloads.PostDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {


    //post create
    public PostDto createPost(PostDto postDto, Long userId,Long categoryId);

    //update post
    public PostDto updatePost(PostDto postDto,Long postId, Long categoryId, Long userId);

    //delete
    public void deletePost(Long postId);

    //get all
    public PostApiResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,String dir);

    //get by category
    public PostApiResponse getPostByCategoryId(Long categoryId,Integer pageNumber, Integer pageSize, String sortBy, String dir);

    //get by postid
    public PostDto getByPostId(Long postId);

    //get by user id
    public PostApiResponse getByUserId(Long userId, Integer pageNumber, Integer pageSize, String sortBy, String dir);

    public List<PostDto> searchPost(String keyword);

}
