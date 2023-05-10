package com.example.userservice.model.account;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.userservice.model.user.Administrator;
import lombok.Data;

@Data
public class MiddleAccount{
    @TableId(value = "middle_account_id" ,type = IdType.ASSIGN_ID)
    private String middleAccountId;
    @TableField(exist = false)
    public static final String MIDDLE_ACCOUNT_ID = String.valueOf(1);
    @TableField(exist = false)
    private Account account;
    @TableField(exist = false)
    private Administrator administrator;
}
