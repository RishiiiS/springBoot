package com.wpits.Demo.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    
    private String address;

    @Transient
    private Long productId; // Used for request body, not saved in DB

    private String productTypePurchased;

    private String productPurchased;
    

    private Double price;
    
    private Integer quantityPurchased;
    
    private Double totalPrice;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime purchaseDate;
}
