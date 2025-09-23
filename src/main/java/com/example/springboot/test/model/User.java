package com.example.springboot.test.model;

import jakarta.validation.constraints.NotBlank;

public class User {
    @NotBlank
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String username;
    private String email;
    private String phone;
    private String website;

    public User() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
}
