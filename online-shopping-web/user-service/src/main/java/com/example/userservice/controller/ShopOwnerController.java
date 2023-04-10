package com.example.userservice.controller;

import com.example.userservice.model.user.ShopOwner;
import com.example.userservice.service.ShopOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/shop-owner")
public class ShopOwnerController {

    @Autowired
    ShopOwnerService shopOwnerService;

    @PostMapping("/find-shop-owner-by-id-number")
    ShopOwner findShopOwner(@RequestBody String idCardNumber){
        return shopOwnerService.findShopOwner(idCardNumber);
    }
}
