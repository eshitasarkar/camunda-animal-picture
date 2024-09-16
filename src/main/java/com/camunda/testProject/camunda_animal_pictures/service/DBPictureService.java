package com.camunda.testProject.camunda_animal_pictures.service;

import com.camunda.testProject.camunda_animal_pictures.entity.PictureDetails;
import com.camunda.testProject.camunda_animal_pictures.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBPictureService {

    @Autowired
    private PictureRepository repository;

    public PictureDetails save(PictureDetails details){
        repository.save(details);
        return details;
    }

    public PictureDetails getPictureObject(Long id){
        return repository.findById(id).orElseThrow();
    }

    public byte[] getPicture(Long id){
        return repository.findById(id).get().getImageData();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<PictureDetails> getAllPictures(){
        return (List<PictureDetails>) repository.findAll();
    }

}

