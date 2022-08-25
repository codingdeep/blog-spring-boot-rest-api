package com.blog.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileServices {
    public String uploadFile(String path, MultipartFile file) throws IOException;
}
