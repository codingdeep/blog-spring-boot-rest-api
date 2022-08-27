package com.blog.controllers;

import com.blog.config.AppConstants;
import com.blog.payloads.CommentDto;
import com.blog.services.CommentServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Resource
    private CommentServices commentServices;

    //CREATING COMMENT FOR PARTICULAR POST BY ID
    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDto> postComment(
            @Valid @RequestBody() CommentDto commentDto,
            @PathVariable("postId") Long postId

    )
    {
        CommentDto commentDto1 = this.commentServices.postComment(commentDto, postId);
        return new ResponseEntity<>(commentDto1, HttpStatus.CREATED);
    }


    //DELETING A SINGLE COMMENT FROM A SPECIFIC POST
    @DeleteMapping("/comment/deleteBy/{commentId}")
    public void deleteComment(@PathVariable("commentId") Long commentId){
        this.commentServices.deleteComment(commentId);
    }

    //UPDATING COMMENT SPECIALLY FROM ADMIN
    @PutMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @RequestBody() CommentDto commentDto,
            @PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId
    )
    {
       CommentDto commentDto1 = this.commentServices.updateComment(commentDto, postId, commentId);
       return new ResponseEntity<>(commentDto1,HttpStatus.OK);
    }

    //GET ALL COMMENTS
    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllComments(
            @PathVariable("postId") Long postId,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize
    ){
        List<CommentDto> commentDtos = this.commentServices.getAllComments(postId,pageNumber,pageSize);
        return new ResponseEntity<>(commentDtos, HttpStatus.OK);
    }
    @GetMapping("/comment/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("commentId") Long commentId){
        CommentDto commentDto = this.commentServices.getCommentById(commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

}
