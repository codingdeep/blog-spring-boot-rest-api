package com.blog.controllers;


import com.blog.models.Post;
import com.blog.payloads.CategoryDto;
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
    public ResponseEntity<PostDto> createPost(@RequestBody() PostDto postDto, @PathVariable("userId") Long userId, @PathVariable("categoryId") Long categoryId){
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
    ){
       PostDto postDto1 = this.postService.updatePost(postDto,postId,categoryId,userId);
       return  new ResponseEntity<>(postDto1,HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable("postId") Long postId){
        this.postService.deletePost(postId);
    }
    @GetMapping("")
    public ResponseEntity<List<PostDto>> getAllPost(
            @RequestParam(value = "pageNumber",defaultValue = "1",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize
            ){
        List<PostDto> postDtos = this.postService.getAllPost(pageNumber,pageSize);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostByCategoryId(@PathVariable("categoryId") Long categoryId){
        List<PostDto> postDtos = this.postService.getPostByCategoryId(categoryId);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
    @GetMapping("/postId={postId}")
    public ResponseEntity<PostDto> getByPostId(@PathVariable("postId") Long postId){
        PostDto postDto = this.postService.getByPostId(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @GetMapping("/userId={userId}")
    public ResponseEntity<List<PostDto>> getByUserId(@PathVariable("userId") Long userId){
        List<PostDto> postDtos = this.postService.getByUserId(userId);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }






}
