package com.springbootapp.userauth.service;

import com.springbootapp.userauth.model.User;

public interface UserService {
	 void save(User user);

	    User findByUsername(String username);

}
