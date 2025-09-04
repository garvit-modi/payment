package com.blog.payment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payment.dto.CreateCustomerRequest;
import com.blog.payment.dto.CustomerResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;

@RestController
@RequestMapping("/api/stripe")
public class CustomerController {

    @PostMapping("/customers")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CreateCustomerRequest request) throws StripeException {
        // Search for existing customer by email
        Map<String, Object> params = new HashMap<>();
        params.put("email", request.getEmail());
        params.put("limit", 1);

        Customer existingCustomer = null;
        List<Customer> customers = Customer.list(params).getData();
        if (!customers.isEmpty()) {
            existingCustomer = customers.get(0);
        }

        Customer customer;
        if (existingCustomer != null) {
            customer = existingCustomer;
        } else {
            CustomerCreateParams createParams = CustomerCreateParams.builder()
                .setName(request.getName())
                .setEmail(request.getEmail())
                .build();
            customer = Customer.create(createParams);
        }

        CustomerResponse response = new CustomerResponse(customer.getId(), customer.getName(), customer.getEmail());
        return ResponseEntity.status(200).body(response);
    }
}