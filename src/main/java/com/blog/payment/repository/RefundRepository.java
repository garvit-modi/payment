package com.blog.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.payment.entity.Refund;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {
    // You can add custom query methods here if needed
}