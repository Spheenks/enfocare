package com.enfocareservice.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.enfocareservice.entity.MedicalFileEntity;
import com.enfocareservice.repository.MedicalFileRepository;

@Service
public class MedicalFileService {

	@Autowired
	private MedicalFileRepository medicalFileRepository;

	@Value("${medicalfile.dir}")
	private String diagnosisDir;

	public List<String> getRecipentEmailsList(String doctorEmail) {

		return medicalFileRepository.findDistinctPatientEmailsByDoctorEmail(doctorEmail);
	}

	public void uploadDiagnosisFile(String patientEmail, String doctorEmail, MultipartFile file, Long consultationId)
			throws IOException {

		System.err.println("FILENAME : " + file.getName());
		String modifiedEmail = patientEmail.replaceAll("@|\\.", "");
		String directoryPath = diagnosisDir + File.separator + modifiedEmail;
		File directory = new File(directoryPath);
		if (!directory.exists()) {
			directory.mkdirs();
		}

		String originalFilename = file.getOriginalFilename();
		String filePath = Paths.get(directoryPath, originalFilename).toString();

		// Save the file locally
		Files.copy(file.getInputStream(), Path.of(filePath));

		// Generate a password for the file, assuming generatePassword() is implemented
		// elsewhere
		String password = generatePassword();

		// Create and save the MedicalFileEntity
		MedicalFileEntity medicalFileEntity = new MedicalFileEntity();
		medicalFileEntity.setPatientEmail(patientEmail);
		medicalFileEntity.setDoctorEmail(doctorEmail);
		medicalFileEntity.setFilePath(filePath);
		medicalFileEntity.setPassword(password);
		medicalFileEntity.setConsultationId(consultationId);

		medicalFileRepository.save(medicalFileEntity);
	}

	// Placeholder for the generatePassword method
	private String generatePassword() {
		// Implement password generation logic here
		return "generatedPassword";
	}

	public List<String> getFilePathsForPatient(String patientEmail) {
		return medicalFileRepository.findFilePathsByPatientEmail(patientEmail);
	}

}
