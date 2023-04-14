package com.example.userservice.model.user.connect;

import lombok.Data;

@Data
public class UserToShoppingCart {
    private Long id;
    private Long userId;
    private Long shoppingCartId;
}
