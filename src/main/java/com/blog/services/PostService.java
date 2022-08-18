package com.blog.services;

import com.blog.models.Post;
import com.blog.payloads.PostDto;

import java.util.List;

public interface PostService {


    //post create
    public PostDto createPost(PostDto postDto, Long userId,Long categoryId);

    //update post
    public PostDto updatePost(PostDto postDto);

    //delete
    public void deletePost(Long postId);

    //get all
    public List<PostDto> getAllPost();

    //get by category
    public List<PostDto> getPostByCategoryId(Long categoryId);

    //get by postid
    public Post getByPostId(Long postId);

    //get by user id
    public List<PostDto> getByUserId(Long userId);

}
