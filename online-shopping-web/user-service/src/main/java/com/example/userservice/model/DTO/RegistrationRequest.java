package com.example.userservice.model.DTO;

import com.example.userservice.model.user.info.auth.UserAuthentication;
import com.example.userservice.model.user.info.auth.UserRole;
import com.example.userservice.model.user.info.basic.UserBasicInfo;
import lombok.Data;

@Data
public class RegistrationRequest {
    private UserBasicInfo userBasicInfo;
    private UserAuthentication userAuthentication;
    private UserRole userRole;
}
