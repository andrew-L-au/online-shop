package com.example.userservice.model.account.connect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserToAccount {
    private Long id;
    private Long userId;
    private Long accountId;
}
