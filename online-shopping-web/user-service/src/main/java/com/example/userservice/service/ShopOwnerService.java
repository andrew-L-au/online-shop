package com.example.userservice.service;

import com.example.userservice.model.connect.ShopOwnerToUser;
import com.example.userservice.model.user.ShopOwner;
import com.example.userservice.model.user.User;
import com.example.userservice.repository.ShopOwnerRepository;
import com.example.userservice.repository.mapper.user.ShopOwnerMapper;
import com.example.userservice.repository.mapper.connect.ShopOwnerToUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopOwnerService extends UserService{
    @Autowired
    ShopOwnerRepository shopOwnerRepository;

    @Autowired
    ShopOwnerMapper shopOwnerMapper;

    @Autowired
    ShopOwnerToUserMapper shopOwnerToUserMapper;



    public boolean registerNewUser(ShopOwner shopOwner){
        this.registerNewUser(shopOwner.getUser());
        shopOwnerMapper.insert(shopOwner);
        ShopOwnerToUser shopOwnerToUser = new ShopOwnerToUser();
        shopOwnerToUser.setUserId(shopOwner.getUser().getUserId());
        shopOwnerToUser.setShopOwnerId(shopOwner.getShopOwnerId());
        shopOwnerToUserMapper.insert(shopOwnerToUser);
        return true;
    }

    public ShopOwner findShopOwner(String idCardNumber){
        User user = this.findUser(idCardNumber);
        ShopOwner shopOwner = shopOwnerRepository.selectShopOwner(user);
        shopOwner.setUser(user);
        return shopOwner;
    }
}
