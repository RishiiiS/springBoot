package com.wpits.Demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wpits.Demo.entity.Partner;
import com.wpits.Demo.repositories.PartnerRepository;
import com.wpits.Demo.service.PartnerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;

    @Override
    public Partner createPartner(Partner partner) {
        return partnerRepository.save(partner);
    }

    @Override
    public Partner getPartner(Long id) {
        Optional<Partner> partner = partnerRepository.findById(id);
        return partner.orElse(null);
    }

    @Override
    public List<Partner> getAllPartners() {
        return partnerRepository.findAll();
    }

    @Override
    public Partner updatePartner(
            Long id,
            Partner partnerDetails
    ) {

    Optional<Partner> optionalPartner =
            partnerRepository.findById(id);

    if (optionalPartner.isPresent()) {

        Partner existingPartner =
                optionalPartner.get();

        // Validate age
        if (partnerDetails.getAge() < 18) {
            throw new RuntimeException(
                "Partner age must be 18 or above"
            );
        }

        existingPartner.setName(
                partnerDetails.getName());

        existingPartner.setAge(
                partnerDetails.getAge());

        existingPartner.setAddress(
                partnerDetails.getAddress());

        return partnerRepository
                .save(existingPartner);
    }

    return null;
}

    @Override
    public void deletePartner(Long id) {
        partnerRepository.deleteById(id);
    }

    @Override
    public boolean existsByMsisdn(String msisdn) {
        return partnerRepository.existsByMsisdn(msisdn);
    }
}
