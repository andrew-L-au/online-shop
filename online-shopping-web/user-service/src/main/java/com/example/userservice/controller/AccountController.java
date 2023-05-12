package com.example.userservice.controller;

import com.example.userservice.controller.accountDTO.AccountOfShopDTO;
import com.example.userservice.controller.accountDTO.AccountOfUserDTO;
import com.example.userservice.controller.accountDTO.RedeemAccountDTO;
import com.example.userservice.model.account.Account;
import com.example.userservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping(path = "/account-of-user")
    public Account accountOfUser(@RequestBody AccountOfUserDTO accountOfUserDTO) {
        String userId = accountOfUserDTO.getUserId();
        if (userId == null){
            return null;
        }
        try {
            return accountService.accountOfUser(userId);
        }catch (RuntimeException e) {
            return null;
        }
    }

    @GetMapping(path = "/middle-account")
    public Account middleAccount() {
        try {
            return accountService.middleAccount();
        }catch (RuntimeException e) {
            return null;
        }
    }

    @GetMapping(path = "/profit-account")
    public Account profitAccount() {
        try {
            return accountService.profitAccount();
        }catch (RuntimeException e) {
            return null;
        }
    }

    @PostMapping(path = "/account-of-shop")
    public Account accountOfShop(@RequestBody AccountOfShopDTO accountOfShopDTO){
        if (accountOfShopDTO == null || accountOfShopDTO.getShopId() == null){
            return null;
        }
        String shopId = accountOfShopDTO.getShopId();
        try {
            return accountService.accountOfShop(shopId);
        }catch (RuntimeException e) {
            return null;
        }
    }

    @PostMapping(path = "/redeem-account")
    public Boolean redeemAccount(@RequestBody RedeemAccountDTO redeemAccountDTO) {
        String accountId = redeemAccountDTO.getAccountId();
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
