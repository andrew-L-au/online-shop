package com.example.userservice.controller.shoppingcartDTO;

import lombok.Data;

@Data
public class AddMerchandiseToShoppingCartDTO {
    private String userId;
    private String merchandiseId;
}
