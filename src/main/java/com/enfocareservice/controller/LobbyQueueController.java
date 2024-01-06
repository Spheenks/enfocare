package com.enfocareservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enfocareservice.model.DeleteQueueRequest;
import com.enfocareservice.model.LobbyQueue;
import com.enfocareservice.service.LobbyQueueService;

@RestController
@RequestMapping("/enfocare/queue")
public class LobbyQueueController {

	@Autowired
	private LobbyQueueService lobbyQueueService;

	@GetMapping("/count/{email}")
	public ResponseEntity<Integer> countEntries(@RequestParam String email) {
		Integer count = lobbyQueueService.countEntries(email);
		return new ResponseEntity<>(count, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<LobbyQueue> saveEntry(@RequestBody LobbyQueue lobbyQueue) {
		LobbyQueue savedQueue = lobbyQueueService.saveEntry(lobbyQueue);
		return new ResponseEntity<>(savedQueue, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteEntityByDoctorAndPatient(@RequestBody DeleteQueueRequest deleteQueueRequest) {
		lobbyQueueService.deleteEntityByDoctorAndPatient(deleteQueueRequest.getDoctor(),
				deleteQueueRequest.getPatient());
		return new ResponseEntity<>("Entity deleted successfully", HttpStatus.OK);
	}

}
