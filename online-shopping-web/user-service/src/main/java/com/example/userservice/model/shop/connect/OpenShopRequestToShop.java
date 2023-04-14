package com.example.userservice.model.shop.connect;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenShopRequestToShop {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long openShopRequestId;
    private Long shopId;
}
