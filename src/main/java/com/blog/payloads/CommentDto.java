package com.blog.payloads;


import com.blog.models.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    private Long id;
    private String comment;
    private String status;

    private PostDto post;
}
