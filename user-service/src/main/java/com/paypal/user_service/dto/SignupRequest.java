package com.paypal.user_service.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class SignupRequest {
    private String name;

    private String email;

    private String password;
    @Setter(AccessLevel.NONE)
    private String adminKey;

    public SignupRequest() {}

    public SignupRequest(String name, String email, String password, String adminKey) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.adminKey = adminKey;
    }
}
