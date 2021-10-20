package com.jarida.server.jaridaserver.cloudinary_image_upload_2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int precio;
    private String description;
    private int peso;
    private String name;
    private String imagenUrl;

    private String imagenId;

    public Imagen(String name, String imagenUrl, String imagenId, int precio, String description, int peso) {
        this.name = name;
        this.imagenUrl = imagenUrl;
        this.imagenId = imagenId;
        this.precio = precio;
        this.description = description;
        this.peso = peso;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public String getImagenId() {
        return imagenId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public void setImagenId(String imagenId) {
        this.imagenId = imagenId;
    }
}
