package com.egypt.daily.life.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.egypt.daily.life.shopping.domain.Response;
import com.egypt.daily.life.shopping.model.ContactUs;
import com.egypt.daily.life.shopping.model.User;
import com.egypt.daily.life.shopping.service.ContactUsService;

@RestController
public class ContactUsController {

	@Autowired
	private ContactUsService  contactUsService ;
	@GetMapping("/getMessages")
	public ResponseEntity<List<ContactUs>> getAllMessages() {
		List<ContactUs>users=contactUsService.findAll() ;
		return new ResponseEntity<List<ContactUs>>(users , HttpStatus.OK) ;
	}
	
	@PostMapping("/setMessages")
	public ResponseEntity<Response> setMessages(@RequestBody ContactUs contactUs) {
		ContactUs contactUsDB = contactUsService.save(contactUs) ;
		if(contactUsDB != null) {
			return new ResponseEntity<Response>(new Response("your recomendations we are recived  successfully"), HttpStatus.OK) ;
		} else {
			return null ;
		}
	}
}
