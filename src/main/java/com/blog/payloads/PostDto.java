package com.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import com.blog.models.Category;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    private Long postId;
    private String title;
    private String description;
    private String postImage;
    private CategoryDto category;
    private UserDto user;
    private Date createdAt;

}
