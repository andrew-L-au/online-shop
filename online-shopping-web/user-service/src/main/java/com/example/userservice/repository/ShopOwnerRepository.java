package com.example.userservice.repository;

import com.example.userservice.model.connect.ShopOwnerToUser;
import com.example.userservice.model.user.ShopOwner;
import com.example.userservice.model.user.User;
import com.example.userservice.repository.mapper.connect.ShopOwnerToUserMapper;
import com.example.userservice.repository.mapper.user.ShopOwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShopOwnerRepository {
    @Autowired
    private ShopOwnerMapper shopOwnerMapper;

    @Autowired
    private ShopOwnerToUserMapper shopOwnerToUserMapper;

    public ShopOwner selectShopOwner(User user) throws RuntimeException{
        ShopOwnerToUser shopOwnerToUser;
        try {
            shopOwnerToUser = shopOwnerToUserMapper.selectShopOwnerToUserByUser(user);
        }catch (RuntimeException runtimeException){
            throw runtimeException;
        }
        return shopOwnerMapper.selectById(shopOwnerToUser.getShopOwnerId());
    }
}
