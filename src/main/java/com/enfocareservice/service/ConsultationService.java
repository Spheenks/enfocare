package com.enfocareservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enfocareservice.entity.ConsultationEntity;
import com.enfocareservice.model.Consultation;
import com.enfocareservice.model.mapper.ConsultationMapper;
import com.enfocareservice.repository.ConsultationRepository;

@Service
public class ConsultationService {

	@Autowired
	private ConsultationMapper consultationMapper;

	@Autowired
	private ConsultationRepository consultationRepository;

	public Consultation save(Consultation consultation) {

		ConsultationEntity consultationEntity = new ConsultationEntity();

		consultationEntity.setAilment(consultation.getAilment());
		consultationEntity.setDate(consultation.getDate());
		consultationEntity.setDiagnosis(consultation.getDiagnosis());
		consultationEntity.setDoctor(consultation.getDoctor());
		consultationEntity.setPatient(consultation.getPatient());
		consultationEntity.setSymptoms(consultation.getSymptoms());
		consultationEntity.setTreatment(consultation.getTreatment());

		return consultationMapper.map(consultationRepository.save(consultationEntity));
	}

	public List<Consultation> findByDoctor(String doctor) {
		List<ConsultationEntity> entities = consultationRepository.findByDoctor(doctor);
		return entities.stream().map(entity -> consultationMapper.map(entity)).collect(Collectors.toList());
	}

	public List<Consultation> findByPatient(String patient) {
		List<ConsultationEntity> entities = consultationRepository.findByPatient(patient);
		return entities.stream().map(entity -> consultationMapper.map(entity)).collect(Collectors.toList());
	}

}
