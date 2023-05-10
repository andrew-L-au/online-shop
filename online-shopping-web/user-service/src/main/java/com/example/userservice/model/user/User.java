package com.example.userservice.model.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.userservice.model.user.info.auth.UserAuthentication;
import com.example.userservice.model.user.info.basic.UserBasicInfo;
import com.example.userservice.model.user.info.auth.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user")
public class User {
    @TableId(value = "user_id",type = IdType.ASSIGN_ID)
    private String userId;
    @TableField(exist = false)
    private UserBasicInfo userBasicInfo;
    @TableField(exist = false)
    private UserAuthentication userAuthentication;
    private UserRole userRole;
}
