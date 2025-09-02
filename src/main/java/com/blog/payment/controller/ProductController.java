package com.blog.payment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.PriceCollection;

@RestController
@RequestMapping("/api/stripe")
public class ProductController {

    @GetMapping("/prices")
    public List<Price> listPrices() throws StripeException {
        PriceCollection prices = Price.list(Map.of("limit", 10));
        return prices.getData();
    }
}
