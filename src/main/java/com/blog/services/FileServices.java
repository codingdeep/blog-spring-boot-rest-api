package com.blog.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileServices {
    public String uploadFile(String path, MultipartFile file) throws IOException;
    public InputStream getResource(String path,String filename) throws FileNotFoundException;
}
