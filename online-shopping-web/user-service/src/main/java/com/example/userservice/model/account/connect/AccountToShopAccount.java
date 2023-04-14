package com.example.userservice.model.account.connect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountToShopAccount {
    private Long id;
    private Long accountId;
    private Long shopAccountId;
}
