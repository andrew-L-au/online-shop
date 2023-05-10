package com.example.userservice.model.shop;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CloseShopRequest {
    @TableId(value = "close_shop_request_id",type = IdType.ASSIGN_ID)
    private String closeShopRequestId;
    @TableField(exist = false)
    private Shop shop;
    private RequestStatus requestStatus;
}
