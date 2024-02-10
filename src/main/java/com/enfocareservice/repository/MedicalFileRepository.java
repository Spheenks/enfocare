package com.enfocareservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enfocareservice.entity.MedicalFileEntity;

@Repository
public interface MedicalFileRepository extends JpaRepository<MedicalFileEntity, Long> {

}
