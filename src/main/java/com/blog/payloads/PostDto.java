package com.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private String title;
    private String description;
    private String postImage="default.jpg";
    private CategoryDto category;
    private UserDto user;
    private Date createdAt;

}
