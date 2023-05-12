package com.example.userservice.model.user.connect;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserToShoppingCart {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String userId;
    private String shoppingCartId;
}
