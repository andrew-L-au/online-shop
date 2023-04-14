package com.example.userservice.model.account.connect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopToAccount {
    private Long id;
    private Long shopId;
    private Long accountId;
}
