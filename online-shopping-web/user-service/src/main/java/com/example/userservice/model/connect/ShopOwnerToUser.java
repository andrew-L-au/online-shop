package com.example.userservice.model.connect;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("shop_owner_to_user")
public class ShopOwnerToUser {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String shopOwnerId;
    private String userId;
}
