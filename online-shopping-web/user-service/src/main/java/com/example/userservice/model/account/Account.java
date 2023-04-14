package com.example.userservice.model.Account;

import lombok.Data;

@Data
public abstract class Account {
    private Long accountId;
    private Double balance;
    public void charge(Double amount){
        balance += amount;
    }
}
