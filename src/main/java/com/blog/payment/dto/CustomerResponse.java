package com.blog.payment.dto;

public class CustomerResponse {
    private String id;
    private String name;
    private String email;

    public CustomerResponse(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}