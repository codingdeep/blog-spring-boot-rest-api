package com.blog.payloads;


import com.blog.models.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    private Long id;
    @NotBlank
    private String comment;
    private String status;
    private Date createdAt;
    private PostDto post;
}
