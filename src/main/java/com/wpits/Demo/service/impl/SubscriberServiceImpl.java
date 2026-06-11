package com.wpits.Demo.service.impl;

// import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wpits.Demo.entity.Subscriber;
import com.wpits.Demo.repositories.SubscriberRepository;
import com.wpits.Demo.service.SubscriberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriberServiceImpl implements SubscriberService{
	
	//@Autowired
	
	private final SubscriberRepository subscriberRepository;

	@Override
	public Subscriber saveSuscriber(Subscriber subscriber) {

		Subscriber savedSubscriber = subscriberRepository.save(subscriber);
		return savedSubscriber;
	}

	@Override
	public Subscriber getSubscriber(Long id) {
		Subscriber subscriber = subscriberRepository.findById(id).get();
		return subscriber;
	}

}
