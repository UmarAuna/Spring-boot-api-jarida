package com.jarida.server.jaridaserver.cloudinary_image_upload_1.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image extends BaseEntity{

    @Column(name = "format", nullable = false)
    private String format;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "secure_url", nullable = false)
    private String secure_url;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "owner", nullable = false)
    private String owner;
}
