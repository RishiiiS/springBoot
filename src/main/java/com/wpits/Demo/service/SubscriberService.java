package com.wpits.Demo.service;

// import org.jspecify.annotations.Nullable;

import com.wpits.Demo.entity.Subscriber;

public interface SubscriberService {

	Subscriber saveSuscriber(Subscriber subscriber);

	
	Subscriber getSubscriber(Long id);
}
