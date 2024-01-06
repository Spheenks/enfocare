package com.enfocareservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enfocareservice.entity.LobbyQueueEntity;
import com.enfocareservice.model.LobbyQueue;
import com.enfocareservice.model.mapper.LobbyQueueMapper;
import com.enfocareservice.repository.LobbyQueueRepository;

@Service
public class LobbyQueueService {

	@Autowired
	private LobbyQueueRepository lobbyQueueRepository;

	@Autowired
	private LobbyQueueMapper lobbyQueueMapper;

	public Integer countEntries(String email) {
		return lobbyQueueRepository.countByDoctor(email);
	}

	public LobbyQueue saveEntry(LobbyQueue lobbyQueue) {

		LobbyQueueEntity lobbyQueueEntity = new LobbyQueueEntity();

		lobbyQueueEntity.setDoctor(lobbyQueue.getDoctor());
		lobbyQueueEntity.setPatient(lobbyQueueEntity.getPatient());
		lobbyQueueEntity.setTimeIn(lobbyQueue.getTimeIn());

		return lobbyQueueMapper.map(lobbyQueueRepository.save(lobbyQueueEntity));

	}

	public void deleteEntityByDoctorAndPatient(String doctor, String patient) {
		// Delete entities by email
		lobbyQueueRepository.deleteByDoctorAndPatient(doctor, patient);

		// You can check if any entities were deleted by querying again or use the
		// return type of the delete method
		// For example, you might want to log a message if no entities were deleted
		int countAfterDeletion = lobbyQueueRepository.countByDoctor(doctor);
		if (countAfterDeletion == 0) {
			System.out.println("No entities found for email: " + doctor);
		}
	}

}
