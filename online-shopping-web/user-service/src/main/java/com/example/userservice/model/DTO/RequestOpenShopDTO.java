package com.example.userservice.model.DTO;

import com.example.userservice.model.shop.Shop;
import lombok.Data;

@Data
public class RequestOpenShopDTO {
    private Shop shop;
    private String idCardNumber;
}
