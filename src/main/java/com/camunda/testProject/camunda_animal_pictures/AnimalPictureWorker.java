package com.camunda.testProject.camunda_animal_pictures;

import com.camunda.testProject.camunda_animal_pictures.entity.PictureDetails;
import com.camunda.testProject.camunda_animal_pictures.service.ApiService;
import com.camunda.testProject.camunda_animal_pictures.service.DBPictureService;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class AnimalPictureWorker {
    private final static Logger LOG = LoggerFactory.getLogger(AnimalPictureWorker.class);

    @Autowired
    private ApiService service;
    @Autowired
    private DBPictureService pictureService;
    @Autowired
    private RestTemplate restTemplate;

    @JobWorker(type = "find-selected-picture", timeout = 60000)
    public void findSelectedPicture(final JobClient client, final ActivatedJob job) {
        final String message_content = (String) job.getVariablesAsMap().get("selectedAnimal");
        LOG.info(message_content);
        byte[] pic = service.getApiData(message_content);
        String encodedPic = Base64.getEncoder().encodeToString(pic);
//        LOG.info("image", encodedPic);
        PictureDetails details = pictureService.saveFile(new PictureDetails(pic));
        LOG.info("data saved, id: ", details.getId());
//        client.newCompleteCommand(job.getKey()).variables("{\"picture\":\""+encodedPic+"\"}")
//                .send().join();
        client.newCompleteCommand(job.getKey()).variables("{\"pictureId\":\""+details.getId()+"\"}")
                .send().join();
    }

   /* @JobWorker(type = "find-cat-picture")
    public void findCatPicture(final JobClient client, final ActivatedJob job) {
        final String message_content = (String) job.getVariablesAsMap().get("selectedAnimal");
        LOG.info(message_content);
        ResponseEntity<Cat[]> catResponse = restTemplate.getForEntity("https://api.thecatapi.com/v1/images/search", Cat[].class);
        LOG.info(catResponse.getBody().toString());
        byte[] image= restTemplate.getForObject(catResponse.getBody()[0].getUrl(),byte[].class);
        LOG.info("image", image);
//                service.getApiData(message_content);
//        client.newCompleteCommand(job.getKey()).variables("{\"image\":\""+image+"\"}")
//                .send().join();
    }
    @JobWorker(type = "find-dog-picture")
    public void findDogPictures(final JobClient client, final ActivatedJob job) {
        final String message_content = (String) job.getVariablesAsMap().get("selectedAnimal");
        LOG.info(message_content);

        byte[] image= restTemplate.getForObject("https://place.dog/300/200", byte[].class);
//                service.getApiData(message_content);
        LOG.info("image", image);
//        client.newCompleteCommand(job.getKey()).variables("{\"image\":\""+image+"\"}")
//                .send().join();
    }
    @JobWorker(type = "find-bear-picture")
    public void findBearPictures(final JobClient client, final ActivatedJob job) {
        final String message_content = (String) job.getVariablesAsMap().get("selectedAnimal");
        LOG.info(message_content);

        byte[] image= restTemplate.getForObject("https://placebear.com/g/200/300", byte[].class);
//                service.getApiData(message_content);
        LOG.info("image", image);
//        client.newCompleteCommand(job.getKey()).variables("{\"image\":\""+image+"\"}")
//                .send().join();
    }*/
}
