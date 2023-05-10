package com.example.userservice.model.account;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.userservice.model.user.ShopOwner;
import lombok.Data;

@Data
public class ShopAccount{
    @TableId(value = "shop_account_id" ,type = IdType.ASSIGN_ID)
    private String shopAccountId;
    @TableField(exist = false)
    private Account account;
    @TableField(exist = false)
    private ShopOwner shopOwner;
}
