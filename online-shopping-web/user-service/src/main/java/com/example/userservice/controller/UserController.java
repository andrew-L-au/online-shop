package com.example.userservice.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.userservice.model.Account.PersonalAccount;
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

    @PostMapping(path = "/shop-owner/registration")
     String shopOwnerRegistration(@RequestBody RegistrationRequest registrationRequest) {
        UserBasicInfo userBasicInfo = registrationRequest.getUserBasicInfo();
        UserAuthentication userAuthentication = registrationRequest.getUserAuthentication();
        if (userBasicInfo == null || userAuthentication == null){
            return "information uncompleted";
        }
        if (userBasicInfo.getUsername() == null || userBasicInfo.getIdCardNumber() == null || userBasicInfo.getUsername() == null || userBasicInfo.getUsername() == null){
            return "userBasicInfo uncompleted";
        }
        if (userAuthentication.getCredential() == null || userAuthentication.getPrincipal() == null){
            return "authentication information uncompleted";
        }
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
        try{
            shopOwnerService.registerNewUser(new ShopOwner(), new User(),userBasicInfo,userAuthentication,new PersonalAccount());
        }catch (RuntimeException e) {
            return "false";
        }
        return "success";
    }

    @PostMapping(path = "/customer/registration")
    String customerRegistration(@RequestBody RegistrationRequest registrationRequest) {
        if (registrationRequest == null){
            return "format error";
        }
        UserBasicInfo userBasicInfo = registrationRequest.getUserBasicInfo();
        UserAuthentication userAuthentication = registrationRequest.getUserAuthentication();
        if (userBasicInfo == null || userAuthentication == null){
            return "information uncompleted";
        }
        if (userBasicInfo.getUsername() == null || userBasicInfo.getIdCardNumber() == null || userBasicInfo.getUsername() == null || userBasicInfo.getUsername() == null){
            return "userBasicInfo uncompleted";
        }
        if (userAuthentication.getCredential() == null || userAuthentication.getPrincipal() == null){
            return "authentication information uncompleted";
        }
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
        try{
            customerService.registerNewUser(new Customer(),new User(),userBasicInfo,userAuthentication,new PersonalAccount());
        }catch (RuntimeException e) {
            return "false";
        }
        return "success";
    }

    @PostMapping(path = "/login")
    LoginResponse login(@RequestBody UserAuthentication userAuthentication) throws JsonProcessingException {
        if (userAuthentication == null){
            return new LoginResponse(null,false);
        }
        User user = userService.findUser(userAuthentication);
        if (user == null || user.getUserId() == null){
            return new LoginResponse(null,false);
        }
        UserBasicInfo userBasicInfo = userService.findUserBasicInfo(user.getUserId());
        if (userBasicInfo == null){
            return new LoginResponse(null,false);
        }
        user.setUserBasicInfo(userBasicInfo);
        user.setUserAuthentication(userAuthentication);
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        String token = JWT.create().withClaim("user",userJson).sign(Algorithm.HMAC256("1"));
        return new LoginResponse(token,true);
    }
}