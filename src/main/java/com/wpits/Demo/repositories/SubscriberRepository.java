package com.wpits.Demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wpits.Demo.entity.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long>{

}
