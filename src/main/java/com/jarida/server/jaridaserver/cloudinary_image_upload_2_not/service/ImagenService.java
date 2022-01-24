package com.jarida.server.jaridaserver.cloudinary_image_upload_2_not.service;

import com.jarida.server.jaridaserver.cloudinary_image_upload_2_not.entity.Imagen;
import com.jarida.server.jaridaserver.cloudinary_image_upload_2_not.repository.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagenService {

    @Autowired
    ImagenRepository imagenRepository;

    public List<Imagen> list(){
        return imagenRepository.findByOrderById();
    }

    public Optional<Imagen> getOne(int id){
        return imagenRepository.findById(id);
    }

    public void save(Imagen imagen){
        imagenRepository.save(imagen);
    }

    public void delete(int id){
        imagenRepository.deleteById(id);
    }

    public boolean exists(int id){
        return imagenRepository.existsById(id);
    }

    public Optional<Imagen> getById(int id) {
        return Optional.ofNullable(imagenRepository.findById(id).orElse(null));
    }
}
