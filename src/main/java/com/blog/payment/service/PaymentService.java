package com.blog.payment.service;

import java.util.List;
import java.util.Optional;

import com.blog.payment.entity.Payment;

public interface PaymentService {
    Payment save(Payment payment);
    Optional<Payment> findById(Long id);
    List<Payment> findAll();
    void deleteById(Long id);
}
