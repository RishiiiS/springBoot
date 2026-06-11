package com.wpits.Demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wpits.Demo.entity.Subscriber;
import com.wpits.Demo.service.SubscriberService;

import lombok.RequiredArgsConstructor;

@RestController //handle incoming HTTP request
@RequiredArgsConstructor //create constructor based dependency injection
public class SubscriberController {

	private final SubscriberService subscriberService; //serv
	
	@PostMapping("/save/subscriber")
	public ResponseEntity<Subscriber> saveSubscriber(@RequestBody Subscriber subscriber){
		return ResponseEntity.status(HttpStatus.CREATED).body(subscriberService.saveSuscriber(subscriber));
	}
	
	@GetMapping("/subscriber/id/{id}")
	public ResponseEntity<?> getSubscriber(@PathVariable Long id){
		return ResponseEntity.ok(subscriberService.getSubscriber(id));
	}
}
