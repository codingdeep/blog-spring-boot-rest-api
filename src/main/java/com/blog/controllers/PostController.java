package com.blog.controllers;


import com.blog.payloads.CategoryDto;
import com.blog.payloads.PostDto;
import com.blog.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("postId") Long postId){
        return  null;
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable("postId") Long postId){
        //
    }
    @GetMapping("")
    public ResponseEntity<List<PostDto>> getAllPost(){
        return null;
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostByCategoryId(@PathVariable("categoryId") Long categoryId){
        return null;
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getByPostId(@PathVariable("postId") Long postId){
        return null;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<PostDto>> getByUserId(@PathVariable("userId") Long userId){
        return null;
    }






}
