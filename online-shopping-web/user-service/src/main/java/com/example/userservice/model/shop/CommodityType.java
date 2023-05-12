package com.example.userservice.model.shop;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommodityType {
    @TableId(value = "commodity_type_id",type = IdType.ASSIGN_ID)
    private String commodityTypeId;
    private String commodityType;
}
