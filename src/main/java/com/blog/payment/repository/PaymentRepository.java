package com.blog.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.payment.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
}
