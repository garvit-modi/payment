package com.blog.payment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payment.dto.CreateSubscriptionRequest;
import com.blog.payment.dto.RefundRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.Refund;
import com.stripe.model.Subscription;
import com.stripe.param.RefundCreateParams;
import com.stripe.param.SubscriptionCreateParams;

@RestController
@RequestMapping("/api/stripe")
public class SubscriptionController {

    @PostMapping("/subscriptions")
    public Subscription createSubscription(@RequestBody CreateSubscriptionRequest req) throws StripeException {
        SubscriptionCreateParams params = SubscriptionCreateParams.builder()
                .setCustomer(req.getCustomerId())
                .addItem(
                        SubscriptionCreateParams.Item.builder()
                                .setPrice(req.getPriceId())
                                .setQuantity(req.getQuantity())
                                .build())
                .setPaymentBehavior(SubscriptionCreateParams.PaymentBehavior.DEFAULT_INCOMPLETE)
                .putExtraParam("expand", List.of("latest_invoice.payment_intent"))
                .build();

        return Subscription.create(params);
    }

    @GetMapping("/subscriptions/{id}")
    public Subscription getSubscription(@PathVariable String id) throws StripeException {
        return Subscription.retrieve(id);
    }

    @PostMapping("/subscriptions/{id}/cancel")
    public Subscription cancelSubscription(@PathVariable String id) throws StripeException {
        Subscription subscription = Subscription.retrieve(id);
        return subscription.cancel();
    }

    @PostMapping("/refunds")
    public Refund refund(@RequestBody RefundRequest req) throws StripeException {
        RefundCreateParams params = RefundCreateParams.builder()
                .setCharge(req.getChargeId())
                .setAmount(req.getAmount()) // optional partial refund
                .build();

        return Refund.create(params);
    }

}