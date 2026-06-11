package com.wpits.Demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wpits.Demo.entity.Partner;
import com.wpits.Demo.service.PartnerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;

    @PostMapping("/save/partner")
    public ResponseEntity<?> createPartner(@RequestBody Partner partner) {
        if (partner.getAge() == null || partner.getAge() < 18) {
            return ResponseEntity.badRequest().body("Partner age must be 18 or above");
        }
        if (partner.getMsisdn() == null || partner.getMsisdn().isEmpty()) {
            return ResponseEntity.badRequest().body("MSISDN cannot be empty");
        }
        if (partnerService.existsByMsisdn(partner.getMsisdn())) {
            return ResponseEntity.badRequest().body("Partner with this msisdn already exists");
        }
        Partner savedPartner = partnerService.createPartner(partner);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPartner);
    }

    @GetMapping("/partner/id/{id}")
    public ResponseEntity<?> getPartner(@PathVariable Long id) {
        Partner partner = partnerService.getPartner(id);
        if (partner != null) {
            return ResponseEntity.ok(partner);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Partner not found");
    }

    @GetMapping("/partners")
    public ResponseEntity<List<Partner>> getAllPartners() {
        return ResponseEntity.ok(partnerService.getAllPartners());
    }

    @PutMapping("/partner/update/{id}")
    public ResponseEntity<?> updatePartner(@PathVariable Long id, @RequestBody Partner partnerDetails) {
        Partner existingPartner = partnerService.getPartner(id);
        if (existingPartner != null && partnerDetails.getMsisdn() != null && !partnerDetails.getMsisdn().equals(existingPartner.getMsisdn())) {
            return ResponseEntity.badRequest().body("msisdn can not be changed");
        }

        Partner updatedPartner = partnerService.updatePartner(id, partnerDetails);
        if (updatedPartner != null) {
            return ResponseEntity.ok(updatedPartner);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Partner not found to update");
    }

    @DeleteMapping("/partner/delete/{id}")
    public ResponseEntity<?> deletePartner(@PathVariable Long id) {
        Partner partner = partnerService.getPartner(id);
        if (partner != null) {
            partnerService.deletePartner(id);
            return ResponseEntity.ok("Partner deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Partner not found to delete");
    }
}
