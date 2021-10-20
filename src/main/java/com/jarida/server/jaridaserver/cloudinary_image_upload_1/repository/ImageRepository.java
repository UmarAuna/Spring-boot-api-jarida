package com.jarida.server.jaridaserver.cloudinary_image_upload_1.repository;

import com.jarida.server.jaridaserver.cloudinary_image_upload_1.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
