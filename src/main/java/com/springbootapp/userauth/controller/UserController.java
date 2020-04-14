package com.springbootapp.userauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.springbootapp.userauth.model.User;
import com.springbootapp.userauth.service.Security;
import com.springbootapp.userauth.service.UserService;
import com.springbootapp.userauth.validator.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private Security security;
	@Autowired
	private UserValidator userValidator;
	
	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new User());
		return "registration";
	}
	
	@PostMapping("/registration")
	 public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        security.autoLogin(userForm.getUsername(), userForm.getPassConfirm());

        return "redirect:/welcome";
    }
	
	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if(error!=null)
			model.addAttribute("error", "username and passsword invalid");
		if(logout!=null)
			model.addAttribute("message", "logged out successfully!");
		
		return "login";
	}
	
	@GetMapping({"/","/welcome"})
	public String welcome(Model model) {
		return "welcome";
		}
}
