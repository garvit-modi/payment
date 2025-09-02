package com.blog.payment.dto;

public class RefundRequest {
    private String chargeId;
    private Long amount; // Stripe expects amount in cents

    public String getChargeId() { return chargeId; }
    public void setChargeId(String chargeId) { this.chargeId = chargeId; }
    public Long getAmount() { return amount; }
    public void setAmount(Long amount) { this.amount = amount; }
}