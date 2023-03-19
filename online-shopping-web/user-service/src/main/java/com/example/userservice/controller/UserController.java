package com.example.userservice.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.userservice.model.DTO.LoginResponse;
import com.example.userservice.model.DTO.RegistrationRequest;
import com.example.userservice.model.user.Customer;
import com.example.userservice.model.user.ShopOwner;
import com.example.userservice.model.user.User;
import com.example.userservice.model.user.info.auth.UserAuthentication;
import com.example.userservice.model.user.info.auth.UserRole;
import com.example.userservice.model.user.info.basic.UserBasicInfo;
import com.example.userservice.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ShopOwnerService shopOwnerService;

    @PostMapping(path = "/registration")
    @Transactional
     String registration(@RequestBody RegistrationRequest registrationRequest) throws IOException {
        UserBasicInfo userBasicInfo = registrationRequest.getUserBasicInfo();
        UserAuthentication userAuthentication = registrationRequest.getUserAuthentication();
        UserRole userRole = registrationRequest.getUserRole();
        boolean userNameDuplicate = userService.existOneInfoWithSameUsername(userBasicInfo.getUsername());
        boolean phoneNumberDuplicate = userService.existOneInfoWithSamePhoneNumber(userBasicInfo.getPhoneNumber());
        boolean idCardNumberDuplicate = userService.existOneInfoWithSameIdCardNumber(userBasicInfo.getIdCardNumber());
        boolean emailDuplicate = userService.existOneInfoWithSameEmail(userBasicInfo.getEmail());
        if (userNameDuplicate){
            return "user name duplicate";
        }
        if (phoneNumberDuplicate){
            return "phone number duplicate";
        }
        if (idCardNumberDuplicate){
            return "id card duplicate";
        }
        if (emailDuplicate){
            return "email duplicate";
        }
        if (userService.existUserAuthentication(userAuthentication)){
            return "password duplicate";
        }
        if (userRole.equals(UserRole.CUSTOMER)){
            Customer customer = new Customer();
            customer.setUser(new User(null, userBasicInfo, userAuthentication, userRole));
            customerService.registerNewUser(customer);
        }else if(userRole.equals(UserRole.SHOP_OWNER)){
            ShopOwner shopOwner = new ShopOwner();
            shopOwner.setUser(new User(null, userBasicInfo, userAuthentication, userRole));
            shopOwnerService.registerNewUser(shopOwner);
        }
        return "success";
    }

    @PostMapping(path = "/login")
    @Transactional
    LoginResponse login(@RequestBody UserAuthentication userAuthentication) throws JsonProcessingException {
        User user = userService.findUser(userAuthentication);
        if (user == null){
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setLoginSuccess(false);
            return loginResponse;
        }
        UserBasicInfo userBasicInfo = userService.findUserBasicInfo(user.getUserId());
        user.setUserBasicInfo(userBasicInfo);
        user.setUserAuthentication(userAuthentication);
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        String token = JWT.create().withClaim("user",userJson).sign(Algorithm.HMAC256("1"));
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setLoginSuccess(true);
        return loginResponse;
    }
}
