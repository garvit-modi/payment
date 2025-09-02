package com.blog.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.payment.entity.PaymentEvent;

@Repository
public interface PaymentEventRepository extends JpaRepository<PaymentEvent, Long> {
    // You can add custom query methods here if needed
}