package com.blog.services.implementation;

import com.blog.exceptions.ResourceNotFoundException;
import com.blog.models.Comment;
import com.blog.models.Post;
import com.blog.payloads.CommentDto;
import com.blog.repositories.CommentRepository;
import com.blog.repositories.PostRepository;
import com.blog.services.CommentServices;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImplementation implements CommentServices {

    @Resource
    private PostRepository postRepository;

    @Resource
    private CommentRepository commentRepository;

    @Resource
    private ModelMapper modelMapper;


    //CREATING A COMMENT TO THE POST
    @Override
    public CommentDto postComment(CommentDto commentDto, Long postId) {

        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setCreatedAt(new Date());
        comment.setPost(post);
        comment = this.commentRepository.save(comment);
        return this.modelMapper.map(comment, CommentDto.class);

    }


    //DELETING A COMMENT
    @Override
    public void deleteComment(Long commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        this.commentRepository.delete(comment);
    }

    //UPDATING A COMMENT

    public CommentDto updateComment(CommentDto commentDto, Long postId, Long commentId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        comment.setPost(post);
        comment.setStatus(commentDto.getStatus());
        comment.setComment(commentDto.getComment());
        comment = this.commentRepository.save(comment);
        return this.modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public List<CommentDto> getAllComments(Long postId, Integer pageNumber, Integer pageSize) {

        Pageable p = PageRequest.of(pageNumber, pageSize);

        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        Page<Comment> pages = this.commentRepository.findByPost(p, post);
        List<Comment> comments = pages.getContent();
        return comments.stream().map(comment -> this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", commentId));

        return this.modelMapper.map(comment, CommentDto.class);
    }

}
