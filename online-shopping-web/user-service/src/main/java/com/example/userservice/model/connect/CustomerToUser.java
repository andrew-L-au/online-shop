package com.example.userservice.model.connect;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("customer_to_user")
public class CustomerToUser {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String customerId;
    private String userId;
}
