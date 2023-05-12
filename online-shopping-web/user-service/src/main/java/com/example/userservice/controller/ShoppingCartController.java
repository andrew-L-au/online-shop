package com.example.userservice.controller;

import com.example.userservice.controller.shoppingcartDTO.AddMerchandiseToShoppingCartDTO;
import com.example.userservice.controller.shoppingcartDTO.GetShoppingCartDTO;
import com.example.userservice.controller.shoppingcartDTO.RemoveMerchandiseFromShoppingCartDTO;
import com.example.userservice.model.shoppingcart.ShoppingCart;
import com.example.userservice.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/get-shopping-cart")
    public ShoppingCart getShoppingCart(@RequestBody GetShoppingCartDTO getShoppingCartDTO){
        if (getShoppingCartDTO == null || getShoppingCartDTO.getUserId() == null){
            return null;
        }
        String userId = getShoppingCartDTO.getUserId();
        try {
            return shoppingCartService.getShoppingCart(userId);
        }catch (RuntimeException e){
            return null;
        }

    }

    @PostMapping("/add-merchandise-to-shopping-cart")
    public Boolean addMerchandiseToShoppingCart(@RequestBody AddMerchandiseToShoppingCartDTO addMerchandiseToShoppingCartDTO){
        if (addMerchandiseToShoppingCartDTO == null){
            return false;
        }
        String userId = addMerchandiseToShoppingCartDTO.getUserId();
        String merchandiseId = addMerchandiseToShoppingCartDTO.getMerchandiseId();
        if (userId == null || merchandiseId == null){
            return false;
        }
        try {
            return shoppingCartService.addMerchandiseToShoppingCart(userId, merchandiseId);
        }catch (RuntimeException e){
            return false;
        }
    }

    @PostMapping("/remove-merchandise-from-shopping-cart")
    public Boolean removeMerchandiseFromShoppingCart(@RequestBody RemoveMerchandiseFromShoppingCartDTO removeMerchandiseFromShoppingCartDTO){
        if (removeMerchandiseFromShoppingCartDTO == null){
            return false;
        }
        String userId = removeMerchandiseFromShoppingCartDTO.getUserId();
        String merchandiseId = removeMerchandiseFromShoppingCartDTO.getMerchandiseId();
        if (userId == null || merchandiseId == null){
            return false;
        }
        try {
            return shoppingCartService.removeMerchandiseFromShoppingCart(userId, merchandiseId);
        }catch (RuntimeException e){
            return false;
        }
    }
}
