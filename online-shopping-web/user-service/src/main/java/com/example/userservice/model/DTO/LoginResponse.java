package com.example.userservice.model.DTO;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private Boolean loginSuccess;
}
