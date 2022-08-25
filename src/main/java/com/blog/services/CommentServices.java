package com.blog.services;

import com.blog.payloads.CommentDto;

public interface CommentServices {


    public CommentDto postComment(CommentDto commentDto, Long postId);

    public void deleteComment(Long commentId);

    public CommentDto updateComment(CommentDto commentDto, Long postId, Long commentId);

}
