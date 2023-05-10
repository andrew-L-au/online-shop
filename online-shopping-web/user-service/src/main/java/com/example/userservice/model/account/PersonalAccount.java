package com.example.userservice.model.account;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.userservice.model.user.User;
import lombok.Data;

@Data
public class PersonalAccount{
    @TableId(value = "personal_account_id" ,type = IdType.ASSIGN_ID)
    private String personalAccountId;
    @TableField(exist = false)
    private Account account;
    @TableField(exist = false)
    private User user;
}
