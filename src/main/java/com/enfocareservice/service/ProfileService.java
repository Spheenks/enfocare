package com.enfocareservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enfocareservice.entity.ProfileEntity;
import com.enfocareservice.model.Profile;
import com.enfocareservice.model.mapper.ProfileMapper;
import com.enfocareservice.repository.ProfileRepository;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private ProfileMapper profileMapper;

	public Profile getProfileByEmail(String email) {
		ProfileEntity profileEntity = profileRepository.findByEmail(email);
		return profileEntity != null ? profileMapper.map(profileEntity) : null;
	}

	public Profile createProfile(Profile profile) {

		ProfileEntity profileEntity = new ProfileEntity();

		profileEntity.setAge(profile.getAge());
		profileEntity.setBiometric(profile.getBiometric());
		profileEntity.setBirthday(profile.getBirthday());
		profileEntity.setBmi(profile.getBmi());
		profileEntity.setClassification(profile.getClassification());
		profileEntity.setEmail(profile.getEmail());
		profileEntity.setFirstname(profile.getFirstname());
		profileEntity.setLastname(profile.getLastname());
		profileEntity.setGender(profile.getGender());
		profileEntity.setHeight(profile.getHeight());
		profileEntity.setIsDoctor(profile.getIsDoctor());
		profileEntity.setMedicalField(profile.getMedicalField());
		profileEntity.setPhone(profile.getPhone());
		profileEntity.setProfileSetup(profile.getProfileSetup());
		profileEntity.setWeight(profile.getWeight());
		profileEntity.setBloodType(profile.getBloodType());

		Profile result = profileMapper.map(profileRepository.save(profileEntity));

		return result;
	}
}
