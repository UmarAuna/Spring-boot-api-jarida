package com.jarida.server.jaridaserver.cloudinary_image_upload_1.controller;

import com.jarida.server.jaridaserver.cloudinary_image_upload_1.entity.Image;
import com.jarida.server.jaridaserver.cloudinary_image_upload_1.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Validated
@Api(tags = "Upload Image Cloudinary API v1 - Working Properly")
@SwaggerDefinition(tags = {
        @Tag(name = "Upload Image Cloudinary", description = "This is for getting Upload Image Cloudinary Api")
})
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/image")
    public List<Image> getAll() {
        return imageService.getAll();
    }

    @GetMapping("/image/{id}")
    public Optional<Image> getById(@PathVariable Long id) {
        return imageService.getById(id);
    }

    @PostMapping(path = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Image create(
            @RequestPart(name = "file") MultipartFile file,
            @RequestParam(required = false)String owner) {
        return imageService.createImage(file, owner);
    }

    @PutMapping(path = "/image/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Image updatImage(
            @RequestPart(name = "file") MultipartFile file,
            Long id,
            @RequestParam(required = false) String owner) throws IOException {
      /*  Image image = imageService.getById(id).get();
        Map result = imageService.updateImage(image.getName());*/

        return imageService.updateImage(file, id, owner);
    }

    @DeleteMapping("/image/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id)throws IOException {
        if(!imageService.exists(id))
            return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);
        Image image = imageService.getById(id).get();
        Map result = imageService.deleteCloud(image.getName());
        imageService.delete(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }


}
