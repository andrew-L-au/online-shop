package com.example.userservice.model.shop.connect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopToNewMerchandiseRequest {
    private Long id;
    private Long shopId;
    private Long newMerchandiseRequestId;
}
