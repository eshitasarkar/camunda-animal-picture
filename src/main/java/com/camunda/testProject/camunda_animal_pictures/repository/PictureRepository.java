package com.camunda.testProject.camunda_animal_pictures.repository;

import com.camunda.testProject.camunda_animal_pictures.entity.PictureDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<PictureDetails, Long> {
}
