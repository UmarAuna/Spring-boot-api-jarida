package com.jarida.server.jaridaserver.cloudinary_image_upload_1.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.jarida.server.jaridaserver.cloudinary_image_upload_1.entity.Image;
import com.jarida.server.jaridaserver.cloudinary_image_upload_1.repository.ImageRepository;
import com.jarida.server.jaridaserver.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class ImageService {
   // private static final String CLOUDINARY_URL = "*****";
    private static final String CLOUDINARY_URL = "cloudinary://855727313552936:BEfHJes5oDvWM9QAfFoTyJlWMdo@dxrxviiv8";

    @Autowired
    private ImageRepository imageRepository;

    Cloudinary cloudinary;

    public boolean exists(Long id){
        return imageRepository.existsById(id);
    }

    public Map deleteCloud(String id) throws IOException {
        Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
        return result;
    }

    public void delete(Long id){
        imageRepository.deleteById(id);
    }



    public List<Image> getAll() {
        return imageRepository.findAll();
    }

    public Optional<Image> getById(Long id) {
        return Optional.ofNullable(imageRepository.findById(id).orElse(null));
    }

    public Image createImage(MultipartFile multipartFile, String owner) {
        Image image = new Image();

        File file;
        try {
            file = Files.createTempFile(System.currentTimeMillis() + "",
                    multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().length() - 4)).toFile();
            multipartFile.transferTo(file);

            cloudinary = new Cloudinary(CLOUDINARY_URL);
            Random random = new Random();
            // "resource_type", "raw "
            /*
            * Valid values: image, raw, and video. Default: image.
            * Note: Use the video resource type for all video assets as
            * well as for audio files, such as .mp3.
            * "transformation", new Transformation().crop("pad").width(300).height(400)
            */
            Map params = ObjectUtils.asMap("overwrite", true, "resource_type", "image", "folder", "avatar_user", "invalidate", true);
            Map uploadResult = cloudinary.uploader().upload(file, params);
            image.setName((String) uploadResult.get("public_id"));
            image.setUrl((String) uploadResult.get("url"));
            image.setSecure_url((String) uploadResult.get("secure_url"));
            image.setFormat((String) uploadResult.get("format"));
            image.setOwner(owner);
            //image.setFileName(multipartFile.getOriginalFilename());
            image.setFileName(String.format("%d%s", random.nextLong(), multipartFile.getOriginalFilename()));
            return imageRepository.save(image);

        } catch (IOException e) {
            System.out.println("ImageService.createImage: " + e.getMessage());
            return null;
        }


    }

    @Transactional
    public Image updateImage(MultipartFile multipartFile, Long id, String owner) throws IOException {
        Optional<Image> imageOptional = imageRepository.findById(id);
        cloudinary.uploader().destroy(imageOptional.get().getName(), ObjectUtils.emptyMap());

        if (imageOptional.isPresent()) {
            Image image = imageOptional.get();
            File file;

            try {
                file = Files.createTempFile(System.currentTimeMillis() + "",
                        multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().length() - 4)).toFile();
                multipartFile.transferTo(file);

                cloudinary = new Cloudinary(CLOUDINARY_URL);
                Random random = new Random();
                Map params = ObjectUtils.asMap("overwrite", true, "resource_type", "image", "folder", "avatar_user",  "invalidate", true);
                Map uploadResult = cloudinary.uploader().upload(file, params);
                image.setName((String) uploadResult.get("public_id"));
                image.setUrl((String) uploadResult.get("url"));
                image.setSecure_url((String) uploadResult.get("secure_url"));
                image.setFormat((String) uploadResult.get("format"));
                image.setOwner(owner);
                // image.setFileName(multipartFile.getOriginalFilename());
                image.setFileName(String.format("%d%s", random.nextLong(), multipartFile.getOriginalFilename()));

                return imageRepository.save(image);

            } catch (IOException e) {
                System.out.println("ImageService.createImage: " + e.getMessage());
                return null;
            }
        }else {
            throw new ResourceNotFoundException("Not Found");
            // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*Map uploadResult = cloudc.upload(f.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
            String uploadURL = (String)uploadResult.get("url");
            String uploadedName = (String)uploadResult.get("public_id");
            String transformedImage = cloudc.createURL(uploadedName);
            System.out.println(transformedImage);
            System.out.println("Uploaded: " + uploadURL);
            System.out.println("Name: " + uploadedName);
            toSave.setImageurl(transformedImage);*/

  /*  public String createUrl(String name, int width, int height, String action){
        return cloudinary.url()
                .transformation(new Transformation()
                        .width(width).height(height).border("2px_solid_black")
                        .crop(action))
                .imageTag(name);
    }*/

   /* public String createUrl(String name) {
        //This method generates the URL for the actor's list
        return cloudinary.url().transformation(new Transformation().width(100).height(100).crop("fill").radius(50).gravity("face")
        ).generate(name);

    }*/

   /* public String createSmallImage(String url, int width, int height) {
        //Creates a transformation from the URL provided
        return cloudinary.url().transformation(new Transformation().width(width).height(height).crop("fill").radius(50).gravity("face"))
                .type("fetch").generate(url);

    }*/
}
