package com.enfocareservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enfocareservice.entity.UserEntity;
import com.enfocareservice.model.User;
import com.enfocareservice.model.mapper.UserMapper;
import com.enfocareservice.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	public User addUser(User user) {

		UserEntity userEntity = new UserEntity();

		userEntity.setEmail(user.getEmail());
		userEntity.setPassword(user.getPassword());

		User savedUser = userMapper.map(userRepository.save(userEntity));

		return savedUser;

	}

}
