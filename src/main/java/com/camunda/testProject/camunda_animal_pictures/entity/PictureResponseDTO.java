package com.camunda.testProject.camunda_animal_pictures.entity;

public class PictureResponseDTO {
    private long id;
    private String base64Image;


    public PictureResponseDTO(long id, String base64Image) {
        this.id = id;
        this.base64Image = base64Image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
