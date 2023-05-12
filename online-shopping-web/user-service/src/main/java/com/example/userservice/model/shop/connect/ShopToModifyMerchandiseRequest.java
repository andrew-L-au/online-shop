package com.example.userservice.model.shop.connect;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ShopToModifyMerchandiseRequest {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String shopId;
    private String modifyMerchandiseRequestId;
}
