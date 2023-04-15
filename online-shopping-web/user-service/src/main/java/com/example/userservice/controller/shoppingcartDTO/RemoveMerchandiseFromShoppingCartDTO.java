package com.example.userservice.controller.shoppingcartDTO;

import lombok.Data;

@Data
public class RemoveMerchandiseFromShoppingCartDTO {
    private Long userId;
    private Long merchandiseId;
}
