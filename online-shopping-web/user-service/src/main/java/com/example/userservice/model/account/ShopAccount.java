package com.example.userservice.model.Account;

import com.example.userservice.model.user.ShopOwner;
import lombok.Data;

@Data
public class ShopAccount extends Account{
    private ShopOwner shopOwner;
}
