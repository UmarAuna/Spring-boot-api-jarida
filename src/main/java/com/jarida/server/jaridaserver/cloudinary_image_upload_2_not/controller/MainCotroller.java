package com.jarida.server.jaridaserver.cloudinary_image_upload_2_not.controller;


import com.jarida.server.jaridaserver.cloudinary_image_upload_2_not.entity.Imagen;
import com.jarida.server.jaridaserver.cloudinary_image_upload_2_not.entity.Message;
import com.jarida.server.jaridaserver.cloudinary_image_upload_2_not.service.CloudinaryService;
import com.jarida.server.jaridaserver.cloudinary_image_upload_2_not.service.ImagenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Validated
@Api(tags = "Upload Image Cloudinary API2 v1")
@SwaggerDefinition(tags = {
        @Tag(name = "Upload Image Cloudinary", description = "This is for getting Upload Image Cloudinary 2 Api")
})
public class MainCotroller {

    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    ImagenService imagenService;

    @GetMapping("/image-cloud")
    public ResponseEntity<List<Imagen>> list(){
        List<Imagen> list = imagenService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/image-cloud/{id}")
    public Optional<Imagen> getById(@PathVariable int id) {
        return imagenService.getById(id);
    }

    @PostMapping("/upload-image-cloud")
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile, @RequestParam String prize,
                                    @RequestParam String peso, @RequestParam String description)throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity(new Message("invalid Data"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        Imagen imagen = new Imagen((String)result.get("original_filename"),
                (String)result.get("url"),
                (String)result.get("public_id"), Integer.parseInt(prize), description, Integer.parseInt(peso));
        imagenService.save(imagen);
        return new ResponseEntity(imagen, HttpStatus.OK);
    }

    @DeleteMapping("/delete-image-cloud/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id)throws IOException {
        if(!imagenService.exists(id))
            return new ResponseEntity(new Message("does not exist"), HttpStatus.NOT_FOUND);
        Imagen imagen = imagenService.getOne(id).get();
        Map result = cloudinaryService.delete(imagen.getImagenId());
        imagenService.delete(id);
        return new ResponseEntity(new Message("Deleted successfully") , HttpStatus.OK);
    }
}
