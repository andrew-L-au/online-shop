package com.example.userservice.model.account.connect;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopToAccount {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String shopId;
    private String accountId;
}
