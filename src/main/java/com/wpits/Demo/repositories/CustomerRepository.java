package com.wpits.Demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wpits.Demo.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
