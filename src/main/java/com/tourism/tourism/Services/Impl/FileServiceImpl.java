package com.tourism.tourism.Services.Impl;


import com.tourism.tourism.Services.FileService;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


@Getter
@Setter
@Service
@AllArgsConstructor

public class FileServiceImpl implements FileService {


    @Override
    public String uploadImageSite(String path, MultipartFile file) throws IOException {
        String name=file.getOriginalFilename();
        String randomId= UUID.randomUUID().toString();
        String fileName=randomId.concat(name.substring(name.lastIndexOf(".")));
        String filePath=path + File.separator + fileName;
        File file1=new File(path);
        if (!file1.exists())
        {
            file1.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileName;
    }

    @Override
    public InputStream getImageSite(String path, String imageName) throws FileNotFoundException {

        String fullPath=path + File.separator + imageName;
        InputStream in=new FileInputStream(fullPath);
        return in;
    }
}
