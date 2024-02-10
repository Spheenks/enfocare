package com.enfocareservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enfocareservice.model.Consultation;
import com.enfocareservice.service.ConsultationService;

@RestController
@RequestMapping("/enfocare/consultation")
public class ConsultationController {

	@Autowired
	private ConsultationService consultationService;

	@PostMapping("/save")
	public ResponseEntity<Consultation> save(@RequestBody Consultation consultation) {

		ResponseEntity<Consultation> responseEntity = null;

		if (consultation == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		Consultation result = consultationService.save(consultation);

		if (result != null) {
			responseEntity = ResponseEntity.ok(result);
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}

		return responseEntity;
	}

}
