package com.example.userservice.model.account;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.userservice.model.user.Administrator;
import lombok.Data;

@Data
public class ProfitAccount{
    @TableId(value = "profit_account_id" ,type = IdType.ASSIGN_ID)
    private String profitAccountId;
    @TableField(exist = false)
    public static final String PROFIT_ACCOUNT_ID = String.valueOf(2);
    @TableField(exist = false)
    private Account account;
    @TableField(exist = false)
    private Administrator administrator;
}
