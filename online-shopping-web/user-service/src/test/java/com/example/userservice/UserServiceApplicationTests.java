package com.example.userservice;

import com.example.userservice.model.account.connect.UserToAccount;
import com.example.userservice.model.user.ShopOwner;
import com.example.userservice.model.user.User;
import com.example.userservice.repository.mapper.user.ShopOwnerMapper;
import com.example.userservice.repository.mapper.user.connect.UserToAccountMapper;
import com.example.userservice.service.ShopOwnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

    @Autowired
    UserToAccountMapper userToAccountMapper;

    @Test
    void contextLoads() {
        String userToAccount = userToAccountMapper.selectAccountByUser("1647913231094267906");
    }

}
