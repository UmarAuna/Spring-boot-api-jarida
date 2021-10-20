package com.jarida.server.jaridaserver.upload_image_file_blob.service;

import com.jarida.server.jaridaserver.upload_image_file_blob.model.FileDB;
import com.jarida.server.jaridaserver.upload_image_file_blob.repository.FileDBRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    private final FileDBRepository fileDBRepository;

    public FileStorageService(FileDBRepository fileDBRepository) {
        this.fileDBRepository = fileDBRepository;
    }

    public FileDB store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

        return fileDBRepository.save(FileDB);
    }

    public FileDB getFile(UUID id) {
        return fileDBRepository.findFileById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}
