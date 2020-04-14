package com.springbootapp.userauth.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springbootapp.userauth.model.User;
import com.springbootapp.userauth.repository.RoleRepo;
import com.springbootapp.userauth.repository.UserRepo;
import org.springframework.stereotype.Service;

public class UserServiceImpl implements UserService {
	@Autowired
	private RoleRepo rolerepo;
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(rolerepo.findAll()));
		userrepo.save(user);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userrepo.findByUsername(username);
	}

}
