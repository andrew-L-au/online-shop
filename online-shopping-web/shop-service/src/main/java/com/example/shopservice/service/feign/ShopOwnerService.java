package com.example.shopservice.service.feign;

import model.user.ShopOwner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("user-service")
public interface ShopOwnerService {

    @RequestMapping(method = RequestMethod.POST,value =  "shop-owner/find-shop-owner-by-id-number")
    ShopOwner findShopOwner(String idCardNumber);
}
