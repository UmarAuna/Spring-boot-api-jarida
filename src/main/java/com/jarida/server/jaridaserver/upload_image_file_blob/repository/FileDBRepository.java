package com.jarida.server.jaridaserver.upload_image_file_blob.repository;

import com.jarida.server.jaridaserver.upload_image_file_blob.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, UUID> {

    @Query("SELECT f FROM FileDB f  WHERE f.id = :id")
    Optional<FileDB> findFileById(UUID id);
}
