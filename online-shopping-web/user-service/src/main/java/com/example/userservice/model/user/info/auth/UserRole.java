package com.example.userservice.model.user.info.auth;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

public enum UserRole {
    ADMINISTRATOR("ADMINISTRATOR"),
    CUSTOMER("CUSTOMER"),
    SHOP_OWNER("SHOP_OWNER");

    @EnumValue
    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    String getRole(){
        return this.role;
    }
}
