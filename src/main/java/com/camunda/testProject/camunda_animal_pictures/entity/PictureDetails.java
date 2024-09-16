package com.camunda.testProject.camunda_animal_pictures.entity;

import jakarta.persistence.*;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

@Entity(name = "picture")
public class PictureDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Lob
    private byte[] imageData;

    public PictureDetails() {
    }

    public PictureDetails(byte[] imageData) {
        this.imageData = imageData;
    }

    public PictureDetails(long id, byte[] imageData) {
        Id = id;
        this.imageData = imageData;
    }

    public Long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    @Override
    public String toString() {
        return "PictureDetails{" +
                "Id=" + Id +
                ", imageData=" + Arrays.toString(imageData) +
                '}';
    }

}
