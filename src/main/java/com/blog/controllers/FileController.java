package com.blog.controllers;

import com.blog.models.Post;
import com.blog.payloads.ApiResponse;
import com.blog.payloads.FileResponse;
import com.blog.payloads.PostDto;
import com.blog.services.FileServices;
import com.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Resource
    private FileServices fileServices;

    @Resource
    private PostService postService;

    @Value("${project.image}")
    private String path;

    @Resource
    private ModelMapper modelMapper;

    //METHOD TO UPLOAD FILE

    @PostMapping("/upload/{postId}")
    public ResponseEntity<PostDto> uploadFile(
            @PathVariable("postId") Long postId,
            @RequestParam("image") MultipartFile file
    ) {
        String filename = null;
        PostDto postDto = null;
        try {
            filename = this.fileServices.uploadFile(this.path, file);
            postDto = this.postService.getByPostId(postId);
            postDto.setPostImage(filename);

            postDto = this.postService.updatePost(postDto, postId, postDto.getCategory().getCategoryId(), postDto.getUser().getId());

        } catch (IOException e) {
            e.printStackTrace();
            //return new
        }
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }


    //METHOD TO DOWNLOAD FILE
    @GetMapping(value = "/images/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadFile(
            @PathVariable("name") String name,
            HttpServletResponse response
    ) throws IOException {
        InputStream resource = this.fileServices.getResource(path, name);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());

    }


}
