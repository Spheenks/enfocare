package com.enfocareservice.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

		System.err.println("UPLOAD FILE CALLED");
		try {
			medicalFileService.uploadDiagnosisFile(patientEmail, doctorEmail, file);
			return ResponseEntity.ok("Diagnosis file uploaded successfully");
		} catch (IOException e) {
			System.err.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload diagnosis file");
		}
	}

	@GetMapping("/patients/{doctorEmail}")
	public ResponseEntity<List<String>> getPatientEmails(@PathVariable String doctorEmail) {
		try {
			List<String> patientEmails = medicalFileService.getRecipentEmailsList(doctorEmail);
			if (patientEmails.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(patientEmails);
		} catch (Exception e) {
			System.err.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/files/{patientEmail}")
	public ResponseEntity<List<String>> getPatientFiles(@PathVariable String patientEmail) {
		try {
			List<String> patientFiles = medicalFileService.getFilePathsForPatient(patientEmail);
			if (patientFiles.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(patientFiles);
		} catch (Exception e) {
			System.err.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}
