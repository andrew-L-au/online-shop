package com.example.userservice.controller;

import com.example.userservice.controller.shoppingcartDTO.AddMerchandiseToShoppingCartDTO;
import com.example.userservice.controller.shoppingcartDTO.RemoveMerchandiseFromShoppingCartDTO;
import com.example.userservice.model.shoppingcart.ShoppingCart;
import com.example.userservice.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ShoppingCart getShoppingCart(@RequestBody Long userId){
        if (userId == null){
            return null;
        }
        try {
            return shoppingCartService.getShoppingCart(userId);
        }catch (RuntimeException e){
            return null;
        }

    }

    @PostMapping("/add-merchandise-to-shopping-cart")
    public Boolean addMerchandiseToShoppingCart(AddMerchandiseToShoppingCartDTO addMerchandiseToShoppingCartDTO){
        if (addMerchandiseToShoppingCartDTO == null){
            return false;
        }
        Long userId = addMerchandiseToShoppingCartDTO.getUserId();
        Long merchandiseId = addMerchandiseToShoppingCartDTO.getMerchandiseId();
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
    public Boolean removeMerchandiseFromShoppingCart(RemoveMerchandiseFromShoppingCartDTO removeMerchandiseFromShoppingCartDTO){
        if (removeMerchandiseFromShoppingCartDTO == null){
            return false;
        }
        Long userId = removeMerchandiseFromShoppingCartDTO.getUserId();
        Long merchandiseId = removeMerchandiseFromShoppingCartDTO.getMerchandiseId();
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
