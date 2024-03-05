package com.jarida.server.jaridaserver.upload_image_file_blob.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "files")
public class FileDB {

    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "UUID", strategy = "uuid4")
    @Type(type = "org.hibernate.type.UUIDCharType")
    //@Column(columnDefinition = "CHAR(36)", unique = true)
    private UUID id = UUID.randomUUID();

    private String name;

    private String type;

    /*
    * There’re two kinds of LOB: BLOB and CLOB:
    * BLOB is for storing binary data
    * CLOB is for storing text data
    * */
    //@Lob
    private byte[] data;

    public FileDB() {
    }

    public FileDB(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
