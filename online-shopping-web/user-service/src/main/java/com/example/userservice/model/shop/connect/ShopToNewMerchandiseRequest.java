package com.example.userservice.model.shop.connect;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopToNewMerchandiseRequest {
    private String id;
    private String shopId;
    private String newMerchandiseRequestId;
}
