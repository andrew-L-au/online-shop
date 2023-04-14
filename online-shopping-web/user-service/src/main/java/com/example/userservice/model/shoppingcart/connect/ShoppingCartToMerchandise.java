package com.example.userservice.model.shoppingcart.connect;

import lombok.Data;

@Data
public class ShoppingCartToMerchandise {
    private Long id;
    private Long shoppingCartId;
    private Long merchandiseId;
}
