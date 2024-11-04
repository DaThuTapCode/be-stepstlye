package com.okconde.bestepstyle.feature.filemanagement.controller;

import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Trong Phu on 19/09/2024 09:27
 *
 * @author Trong Phu
 */
@RestController
@RequestMapping("images")
public class ImageController {

    @GetMapping(value = "{imgName}")
    public ResponseEntity<?> getImageByName(
            @PathVariable String imgName
    ){
        try {
            Path imagePath = Paths.get("uploads").resolve(imgName);
            Resource image = new FileSystemResource(imagePath.toFile());
            if(image.exists()) {
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                        .body(image);
            }else {
                throw new ResourceNotFoundException("Không tìm thấy ảnh", imgName);
            }
        }catch (Exception e){
            throw new ResourceNotFoundException("Không tìm thấy ảnh", imgName);
        }
    }
}
