package com.springbootapp.userauth.service;

public interface Security {
	
	String findLoggedInUsername();
    void autoLogin(String username, String password);

}
