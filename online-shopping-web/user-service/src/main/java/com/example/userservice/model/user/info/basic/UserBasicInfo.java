package com.example.userservice.model.user.info.basic;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.userservice.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_basic_info")
public class UserBasicInfo {
    @TableId(type = IdType.ASSIGN_ID)
    private String userBasicInfoId;
    @TableField(exist = false)
    private User user;
    private String username;
    private String phoneNumber;
    private String idCardNumber;
    private String email;
}
