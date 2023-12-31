package com.enfocareservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enfocareservice.model.Profile;
import com.enfocareservice.service.ProfileService;

@RestController
@RequestMapping("/enfocare/profile")
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@GetMapping("/{email}")
	public ResponseEntity<Profile> getProfileByEmail(@PathVariable String email) {

		System.err.println("getProfileByEmail Called");

		System.err.println("getProfileByEmail Called");
		try {
			// Your ProfileService should have a method to retrieve a profile by email
			Profile profile = profileService.getProfileByEmail(email);

			if (profile != null) {
				return ResponseEntity.ok(profile);
			} else {
				// If no profile is found, return 404 Not Found
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			// Handle exceptions, e.g., log the error
			System.err.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/save")
	public ResponseEntity<Profile> saveProfile(@RequestBody Profile profile) {

		System.err.println("profile called" + profile);
		try {
			// Your ProfileService should have a method to save a profile
			Profile savedProfile = profileService.createProfile(profile);
			return ResponseEntity.ok(savedProfile);
		} catch (Exception e) {
			// Handle exceptions, e.g., log the error

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
