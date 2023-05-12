package com.example.userservice.model.shop;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.userservice.model.merchandise.Merchandise;
import com.example.userservice.model.user.ShopOwner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("shop")
public class Shop {
    @TableId(value = "shop_id",type = IdType.ASSIGN_ID)
    private String shopId;
    @TableField(exist = false)
    private ShopBasicInfo shopBasicInfo;
    @TableField(exist = false)
    private ShopOwner shopOwner;
    @TableField(exist = false)
    private List<CommodityType> commodityTypes;
    private ShopStatus shopStatus;
}
