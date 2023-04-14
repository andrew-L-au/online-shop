package com.example.userservice.model.shoppingcart;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.userservice.model.merchandise.Merchandise;
import com.example.userservice.model.user.User;
import lombok.Data;

import java.util.List;

@Data
public class ShoppingCart {
    @TableId("shopping_cart_id")
    private Long shoppingCartId;
    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private List<Merchandise> merchandises;
}
