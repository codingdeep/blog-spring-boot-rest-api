package com.blog.services.implementation;

import com.blog.services.FileServices;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImplement implements FileServices {
    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {

        //file name
        String name = file.getOriginalFilename();

        //generating random name
        String randomId = UUID.randomUUID().toString();
        String generatedName = randomId.concat(name.substring(name.lastIndexOf(".")));



        String filePath = path + File.separator + File.separator + generatedName;


        //create folder if not exist
        File file1 = new File(path);
        if(!file1.exists()){
            file1.mkdir();

        }

        //file copy
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return generatedName;
    }

    @Override
    public InputStream getSource(String path,String filename) throws FileNotFoundException {
        String fullPath = path+File.separator+filename;
        InputStream is = new FileInputStream(fullPath);
        return is;
    }

}
