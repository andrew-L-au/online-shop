package com.example.userservice.model.Account;

import com.example.userservice.model.user.Administrator;
import lombok.Data;

@Data
public class MiddleAccount extends Account{
    private Administrator administrator;
}
