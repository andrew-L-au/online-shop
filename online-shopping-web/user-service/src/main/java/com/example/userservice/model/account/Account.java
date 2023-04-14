package com.example.userservice.model.account;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Account {
    @TableId(value = "account_id" ,type = IdType.ASSIGN_ID)
    private Long accountId;
    private Double balance;
}
