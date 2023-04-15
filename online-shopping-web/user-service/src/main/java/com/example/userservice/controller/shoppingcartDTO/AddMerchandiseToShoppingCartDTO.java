package com.example.userservice.controller.shoppingcartDTO;

import lombok.Data;

@Data
public class AddMerchandiseToShoppingCartDTO {
    private Long userId;
    private Long merchandiseId;
}
