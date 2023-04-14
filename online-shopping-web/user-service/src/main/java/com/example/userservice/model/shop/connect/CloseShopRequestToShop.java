package com.example.userservice.model.shop.connect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CloseShopRequestToShop {
    private Long id;
    private Long closeShopRequestId;
    private Long shopId;
}
