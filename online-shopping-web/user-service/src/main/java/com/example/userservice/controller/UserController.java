package com.example.userservice.controller;

import com.example.userservice.controller.userDTO.ChangePasswordDTO;
import com.example.userservice.controller.userDTO.ChangeUserBasicInfoDTO;
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
    public String shopOwnerRegistration(@RequestBody RegistrationRequest registrationRequest) {
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

    @PostMapping(path = "/basic-info")
    public UserBasicInfo getUserBasicInfo(@RequestBody String userId){
        if (userId == null){
            return null;
        }
        try {
            return userService.findUserBasicInfo(userId);
        }catch (RuntimeException e){
            return null;
        }
    }

    @PostMapping(path = "/customer/registration")
    public String customerRegistration(@RequestBody RegistrationRequest registrationRequest) {
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
    public LoginResponse login(@RequestBody UserAuthentication userAuthentication){
        if (userAuthentication == null){
            return new LoginResponse(null,false);
        }
        try {
            return userService.login(userAuthentication);
        }catch (RuntimeException | JsonProcessingException e){
            return new LoginResponse(null,false);
        }
    }

    @PostMapping(path = "/change-user-basic-info")
    public Boolean changeUserBasicInfo(@RequestBody ChangeUserBasicInfoDTO changeUserBasicInfoDTO){
        String userId = changeUserBasicInfoDTO.getUserId();
        UserBasicInfo userBasicInfo = changeUserBasicInfoDTO.getUserBasicInfo();
        if (userId == null || userBasicInfo == null){
            return false;
        }
        Boolean ret = true;
        try {
            ret = userService.changeUserBasicInfo(userId, userBasicInfo);
        }catch (RuntimeException e){
            return false;
        }
        return ret;
    }

    @PostMapping(path = "/change-password")
    public Boolean changePassword(@RequestBody ChangePasswordDTO changePasswordDTO){
        String userId = changePasswordDTO.getUserId();
        String password = changePasswordDTO.getPassword();
        if (userId == null || password == null){
            return false;
        }
        Boolean ret = true;
        try {
            ret = userService.changePassword(userId, password);
        }catch (RuntimeException e){
            return false;
        }
        return ret;
    }
}
