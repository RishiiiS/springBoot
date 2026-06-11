package com.wpits.Demo.service;

import java.util.List;

import com.wpits.Demo.entity.Partner;

public interface PartnerService {

    Partner createPartner(Partner partner);

    Partner getPartner(Long id);

    List<Partner> getAllPartners();

    Partner updatePartner(Long id, Partner partnerDetails);

    void deletePartner(Long id);

    boolean existsByMsisdn(String msisdn);
}
