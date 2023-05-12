package com.example.userservice.controller.userDTO;

import lombok.Data;

@Data
public class ChangePasswordDTO {
    private String userId;
    private String password;
}
