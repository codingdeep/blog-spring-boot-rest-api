package com.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FileResponse {
    private Long PostId;
    private String filename;
    private String message;

}
