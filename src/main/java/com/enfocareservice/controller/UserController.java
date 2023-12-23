package com.enfocareservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.enfocareservice.model.User;
import com.enfocareservice.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/user/save")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		return ResponseEntity.ok(userService.addUser(user));
	}

}
