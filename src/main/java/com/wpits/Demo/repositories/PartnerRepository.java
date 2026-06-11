package com.wpits.Demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wpits.Demo.entity.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    boolean existsByMsisdn(String msisdn);
}
