package com.example.userservice.model.Account;

import com.example.userservice.model.user.Administrator;
import lombok.Data;

@Data
public class ProfitAccount extends Account{
    private Administrator administrator;
}
