package com.example.userservice.controller;

import com.example.userservice.controller.accountDTO.RedeemAccountDTO;
import com.example.userservice.model.account.Account;
import com.example.userservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping(path = "/account-of-user")
    public Account accountOfUser(@RequestBody Long userId){
        if (userId == null){
            return null;
        }
        try {
            return accountService.accountOfUser(userId);
        }catch (RuntimeException e) {
            return null;
        }
    }

    @PostMapping(path = "/account-of-shop")
    public Account accountOfShop(@RequestBody Long shopId){
        if (shopId == null){
            return null;
        }
        try {
            return accountService.accountOfShop(shopId);
        }catch (RuntimeException e) {
            return null;
        }
    }

    @PostMapping(path = "/account-of-user")
    public Boolean redeemAccount(RedeemAccountDTO redeemAccountDTO) {
        Long accountId = redeemAccountDTO.getAccountId();
        Double amount = redeemAccountDTO.getAmount();
        if (accountId == null) {
            return false;
        }
        try {
            return accountService.redeemAccount(accountId, amount);
        }catch (RuntimeException e){
            return false;
        }
    }
}
