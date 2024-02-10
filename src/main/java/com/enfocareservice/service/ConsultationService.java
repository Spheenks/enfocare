package com.enfocareservice.service;

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

}
