package com.example.userservice.model.shop.connect;

import lombok.Data;

@Data
public class ShopToModifyMerchandiseRequest {
    private Long id;
    private Long shopId;
    private Long modifyMerchandiseRequestId;
}
