package com.example.userservice.model.shop;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("shop_basic_info")
public class ShopBasicInfo {
    @TableId(value = "shop_basic_info_id",type = IdType.ASSIGN_ID)
    private Long shopBasicInfoId;
    @TableField(exist = false)
    private Shop shop;
    private String name;
    private String profile;
    private String address;
    private BigDecimal totalCapital;
    private String registrationDate;
}
