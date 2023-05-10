package com.example.userservice.model.user.info.auth;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.userservice.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user_authentication")
public class UserAuthentication {
    @TableId(type = IdType.ASSIGN_ID)
    private String userAuthenticationId;
    @TableField(exist = false)
    private User user;
    private String principal;
    private String credential;
}
