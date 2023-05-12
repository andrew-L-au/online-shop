package com.example.userservice.controller.shoppingcartDTO;

import lombok.Data;

@Data
public class RemoveMerchandiseFromShoppingCartDTO {
    private String userId;
    private String merchandiseId;
}
