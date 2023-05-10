package com.example.userservice.model.shoppingcart.connect;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ShoppingCartToMerchandise {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String shoppingCartId;
    private String merchandiseId;
}
