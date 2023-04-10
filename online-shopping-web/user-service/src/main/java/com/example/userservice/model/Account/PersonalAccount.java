package com.example.userservice.model.Account;

import com.example.userservice.model.user.User;
import lombok.Data;

@Data
public class PersonalAccount extends Account{
    private User user;
}
