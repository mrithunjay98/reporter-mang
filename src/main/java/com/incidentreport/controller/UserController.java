package com.incidentreport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incidentreport.entity.User;
import com.incidentreport.repo.UserRepository;
import com.incidentreport.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	
	 private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
    	 if (userRepository.findByEmail(user.getEmail()).isPresent()) {
             throw new RuntimeException("User already exists");
         }
       
    	 User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
}

