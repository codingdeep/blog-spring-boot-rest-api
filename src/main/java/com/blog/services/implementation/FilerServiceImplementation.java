package com.blog.services.implementation;

import com.blog.services.FileServices;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FilerServiceImplementation implements FileServices {

    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();

        String uniqueId = UUID.randomUUID().toString();
        String uniqueFileName = uniqueId.concat(filename.substring(filename.lastIndexOf(".")));
        String fullPath = path+ File.separator+uniqueFileName;

        File file1 = new File(path);
        if(!file1.exists()){
            file1.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(fullPath));

        return fullPath;
    }
}
