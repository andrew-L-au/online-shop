package com.example.userservice.model.shop.connect;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopToCommodityType {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String shopId;
    private String commodityTypeId;
}
