package com.enfocareservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enfocareservice.entity.LobbyQueueEntity;

@Repository
public interface LobbyQueueRepository extends JpaRepository<LobbyQueueEntity, Long> {

	Integer countByDoctor(String email);

	void deleteByDoctorAndPatient(String doctor, String patient);

}
