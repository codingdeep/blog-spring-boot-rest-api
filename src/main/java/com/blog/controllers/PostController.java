package com.blog.controllers;


import com.blog.config.AppConstants;
import com.blog.models.Post;
import com.blog.payloads.CategoryDto;
import com.blog.payloads.PostApiResponse;
import com.blog.payloads.PostDto;
import com.blog.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Resource
    private PostService postService;

    //create a post
    @PostMapping("/userId/{userId}/categoryId/{categoryId}")
    public ResponseEntity<PostDto> createPost(@RequestBody() PostDto postDto, @PathVariable("userId") Long userId, @PathVariable("categoryId") Long categoryId) {
        PostDto postDto1 = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(postDto1, HttpStatus.OK);
    }

    //update post by id
    @PutMapping("/postId={postId}&categoryId={categoryId}&userId={userId}")
    public ResponseEntity<PostDto> updatePost(
            @RequestBody() PostDto postDto,
            @PathVariable("postId") Long postId,
            @PathVariable("categoryId") Long categoryId,
            @PathVariable("userId") Long userId
    ) {
        PostDto postDto1 = this.postService.updatePost(postDto, postId, categoryId, userId);
        return new ResponseEntity<>(postDto1, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable("postId") Long postId) {
        this.postService.deletePost(postId);
    }

    @GetMapping("")
    public ResponseEntity<PostApiResponse> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "dir", defaultValue = AppConstants.SORT_DIR, required = false) String dir
    ) {
        PostApiResponse postApiResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, dir);
        return new ResponseEntity<>(postApiResponse, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<PostApiResponse> getPostByCategoryId(
            @PathVariable("categoryId") Long categoryId,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "dir", defaultValue = AppConstants.SORT_DIR, required = false) String dir
    ) {
        PostApiResponse postApiResponse = this.postService.getPostByCategoryId(categoryId, pageNumber, pageSize, sortBy, dir);
        return new ResponseEntity<>(postApiResponse, HttpStatus.OK);
    }

    @GetMapping("/postId={postId}")
    public ResponseEntity<PostDto> getByPostId(@PathVariable("postId") Long postId) {
        PostDto postDto = this.postService.getByPostId(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @GetMapping("/user={userId}")
    public ResponseEntity<PostApiResponse> getByUserId(
            @PathVariable("userId") Long userId,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "dir", defaultValue = AppConstants.SORT_DIR, required = false) String dir
    ) {
        PostApiResponse postApiResponse = this.postService.getByUserId(userId, pageNumber, pageSize, sortBy, dir);
        return new ResponseEntity<>(postApiResponse, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostDto>> searchPost(
            @RequestParam(value = "keyword",defaultValue = "", required = true) String keyword
    ) {
        List<PostDto> postDtos = this.postService.searchPost(keyword);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }


}
