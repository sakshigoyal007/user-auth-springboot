package com.springbootapp.userauth.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

import com.springbootapp.userauth.model.User;
import com.springbootapp.userauth.service.UserService;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class UserValidator implements Validator {
	@Autowired 
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user=(User) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username","NotEmpty");
		if(user.getUsername().length()<6 || user.getUsername().length()>32)
			errors.rejectValue("username","Size.userForm.username");
		if(userService.findByUsername(user.getUsername())!=null)
			errors.rejectValue("username", "Duplicate.userForm.username");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if(user.getPassword().length()<8 || user.getPassword().length()>32)
			errors.rejectValue("password", "Size.userForm.password");
		if(!user.getPassConfirm().equals (user.getPassword()))
			errors.rejectValue("passConfirm","Diff.userForm.passConfirm");
		
	}
	
	
}
