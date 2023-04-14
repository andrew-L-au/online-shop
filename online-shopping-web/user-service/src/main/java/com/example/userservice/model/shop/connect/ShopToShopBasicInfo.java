package com.example.userservice.model.shop.connect;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopToShopBasicInfo {
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long shopToShopBasicInfoId;
    private Long shopId;
    private Long shopBasicInfoId;
}
