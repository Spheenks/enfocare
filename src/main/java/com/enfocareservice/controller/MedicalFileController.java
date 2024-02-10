package com.enfocareservice.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.enfocareservice.service.MedicalFileService;

@RestController
@RequestMapping("/enfocare/medical-file")
public class MedicalFileController {

	@Autowired
	private MedicalFileService medicalFileService;

	@PostMapping("/upload/{patientEmail}/{doctorEmail}")
	public ResponseEntity<String> handleDiagnosisFileUpload(@PathVariable String patientEmail,
			@PathVariable String doctorEmail, @RequestParam("file") MultipartFile file) {
		try {
			medicalFileService.uploadDiagnosisFile(patientEmail, doctorEmail, file);
			return ResponseEntity.ok("Diagnosis file uploaded successfully");
		} catch (IOException e) {
			System.err.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload diagnosis file");
		}
	}

}
