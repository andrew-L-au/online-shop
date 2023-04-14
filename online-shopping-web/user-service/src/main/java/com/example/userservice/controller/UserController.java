package com.example.userservice.controller;

import com.example.userservice.model.account.PersonalAccount;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            shopOwnerService.registerNewUser(new ShopOwner(), new User(null,null,null,UserRole.SHOP_OWNER),userBasicInfo,userAuthentication,new PersonalAccount());
        }catch (RuntimeException e) {
            return "false";
        }
        return "success";
    }

    @PostMapping(path = "/customer/registration")
    String customerRegistration(RegistrationRequest registrationRequest) {
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
            customerService.registerNewUser(new Customer(),new User(null,null,null,UserRole.CUSTOMER),userBasicInfo,userAuthentication,new PersonalAccount());
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
        try {
            return userService.login(userAuthentication);
        }catch (RuntimeException e){
            return new LoginResponse(null,false);
        }
    }
}
