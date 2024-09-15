package com.camunda.testProject.camunda_animal_pictures.service;

import com.camunda.testProject.camunda_animal_pictures.entity.Cat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@org.springframework.stereotype.Service
public class ApiService {

    @Autowired
    private RestTemplate restTemplate;

    private final static Logger LOG = LoggerFactory.getLogger(ApiService.class);


    public byte[] getApiData(String animal){
        if(animal.equals("cat")){
            ResponseEntity<Cat[]> catResponse = restTemplate.getForEntity("https://api.thecatapi.com/v1/images/search", Cat[].class);
            LOG.info(catResponse.getBody().toString());
            return restTemplate.getForObject(catResponse.getBody()[0].getUrl(),byte[].class);
        }else if(animal.equals("dog")){
            return restTemplate.getForObject("https://place.dog/300/200", byte[].class);
        }else if(animal.equals("bear")) {
            return restTemplate.getForObject("https://placebear.com/g/200/300", byte[].class);
        }
        return null;
    }

}
