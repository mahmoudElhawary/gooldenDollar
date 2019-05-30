package com.egypt.daily.life.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.egypt.daily.life.shopping.domain.Response;
import com.egypt.daily.life.shopping.model.User;
import com.egypt.daily.life.shopping.service.UserService;

@RestController
public class PreLoginController {

	@Autowired
	private UserService userService ;
	
	@PostMapping("/signup")
	public ResponseEntity<Response> signup(@RequestBody User user) {
		User userDB = userService.save(user) ;
		if(userDB != null) {
			return new ResponseEntity<Response>(new Response("user is sigup successfully"), HttpStatus.OK) ;
		} else {
			return null ;
		}
	}
	@GetMapping("/getUser/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
		return new ResponseEntity<User>(userService.getUser(id), HttpStatus.OK);
	}
	@PostMapping("/update")
	public ResponseEntity<Response> update(@RequestBody User user) {
		User userDB = userService.updateUser(user) ;
		if(userDB != null) {
			return new ResponseEntity<Response>(new Response("user is sigup successfully"), HttpStatus.OK) ;
		} else {
			return null ;
		}
	}
}
