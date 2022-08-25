package com.blog.controllers;

import com.blog.payloads.CommentDto;
import com.blog.services.CommentServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Resource
    private CommentServices commentServices;


    //CREATING COMMENT FOR PARTICULAR POST BY ID

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDto> postComment(
            @RequestBody() CommentDto commentDto,
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

}
