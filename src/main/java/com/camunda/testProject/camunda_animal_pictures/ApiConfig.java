package com.camunda.testProject.camunda_animal_pictures;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
