package com.jarida.server.jaridaserver.cloudinary_image_upload_1.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@MappedSuperclass
@Data
@NoArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

    @PrePersist
    public void persistCreate() {
        this.dateCreated = LocalDateTime.now();
    }

    @PreUpdate
    public void persistUpdate() {
        this.dateUpdated = LocalDateTime.now();
    }

}
