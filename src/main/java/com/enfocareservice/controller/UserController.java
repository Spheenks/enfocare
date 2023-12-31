package com.enfocareservice.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.enfocareservice.model.ChangePasswordRequest;
import com.enfocareservice.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PatchMapping
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
		userService.changePassword(request, connectedUser);
		return ResponseEntity.ok().build();
	}

}
