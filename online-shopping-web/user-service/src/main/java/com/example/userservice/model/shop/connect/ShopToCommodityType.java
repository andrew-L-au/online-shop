package com.example.userservice.model.shop.connect;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopToCommodityType {
    @TableId
    private Long id;
    private Long shopId;
    private Long commodityTypeId;
}
