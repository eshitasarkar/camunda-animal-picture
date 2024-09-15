package com.camunda.testProject.camunda_animal_pictures;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.camunda.testProject.*"})
public class CamundaAnimalPicturesApplication {

	private final static Logger LOG = LoggerFactory.getLogger(CamundaAnimalPicturesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CamundaAnimalPicturesApplication.class, args);
	}

}
