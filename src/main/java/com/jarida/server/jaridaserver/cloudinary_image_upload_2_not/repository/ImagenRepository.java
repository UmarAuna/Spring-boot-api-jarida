package com.jarida.server.jaridaserver.cloudinary_image_upload_2_not.repository;

import com.jarida.server.jaridaserver.cloudinary_image_upload_2_not.entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Integer> {
    List<Imagen> findByOrderById();
}
