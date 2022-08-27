package com.blog.services;

import com.blog.payloads.CommentDto;

import java.util.List;

public interface CommentServices {


    public CommentDto postComment(CommentDto commentDto, Long postId);

    public void deleteComment(Long commentId);

    public CommentDto updateComment(CommentDto commentDto, Long postId, Long commentId);

    public List<CommentDto> getAllComments(Long postId, Integer pageNumber, Integer pageSize);

    public CommentDto getCommentById(Long commentId);

}
