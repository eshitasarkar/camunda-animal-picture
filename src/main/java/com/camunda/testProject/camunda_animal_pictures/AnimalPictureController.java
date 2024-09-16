package com.camunda.testProject.camunda_animal_pictures;

import com.camunda.testProject.camunda_animal_pictures.entity.PictureDetails;
import com.camunda.testProject.camunda_animal_pictures.entity.PictureResponseDTO;
import com.camunda.testProject.camunda_animal_pictures.service.DBPictureService;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceResult;
import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@EnableZeebeClient
@Deployment(resources = "classpath:animal-pic-serviceTask.bpmn")
public class AnimalPictureController {

    private final static Logger LOG = LoggerFactory.getLogger(AnimalPictureController.class);
    @Autowired
    private DBPictureService pictureService;

    @Autowired
    private ZeebeClient client;


    @GetMapping(value="/chooseAnimal", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> chooseAnimal(@RequestParam String animal){
        // deploy bpmn model and get data from jobWorker
        final ProcessInstanceResult result = client.newCreateInstanceCommand()
                .bpmnProcessId("animal-pictures-id")
                .latestVersion()
                .variables(Map.of("selectedAnimal",animal))
                .withResult()
                .send()
                .join();
        LOG.info("Started instance for processDefinitionKey='{}', bpmnProcessId='{}', version='{}' with processInstanceKey='{}'",
                result.getProcessDefinitionKey(), result.getBpmnProcessId(), result.getVersion(), result.getProcessInstanceKey());

//        String encodedPic = result.getVariablesAsMap().get("picture").toString();
//        byte[] pic = Base64.getDecoder().decode(encodedPic);
        String id = result.getVariablesAsMap().get("pictureId").toString();
        LOG.info("id : "+id);
        PictureDetails details = pictureService.getPictureObject(Long.parseLong(id));
        return ResponseEntity.ok(details.getImageData());
    }

    @GetMapping(value="/getAnimal", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> gatAnimal(@RequestParam Long Id){
        byte[] picture = pictureService.getPicture(Id);
        if(picture != null) {
            return new ResponseEntity(picture, HttpStatus.OK);
        }
        return new ResponseEntity(ResponseEntity.notFound(),HttpStatus.NOT_FOUND);
    }
}
