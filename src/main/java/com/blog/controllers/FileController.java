package com.blog.controllers;

import com.blog.payloads.FileResponse;
import com.blog.services.FileServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Resource
    private FileServices fileServices;

    @Value("${project.image}")
    private String path;
    @PostMapping("/upload")
    public ResponseEntity<FileResponse> uploadFile(
            @RequestParam("image") MultipartFile file
    ){
        String filename = null;
        try {
            filename = this.fileServices.uploadFile(this.path, file);
        }catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity<>(new FileResponse(null,"File Upload failed"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new FileResponse(filename,"File Upload successfull"), HttpStatus.OK);
    }

}
