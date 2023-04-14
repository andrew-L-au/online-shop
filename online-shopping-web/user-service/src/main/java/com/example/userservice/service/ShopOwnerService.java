package com.example.userservice.service;

import com.example.userservice.model.account.PersonalAccount;
import com.example.userservice.model.user.ShopOwner;
import com.example.userservice.model.user.User;
import com.example.userservice.model.user.info.auth.UserAuthentication;
import com.example.userservice.model.user.info.basic.UserBasicInfo;
import com.example.userservice.repository.ShopOwnerRepository;
import com.example.userservice.repository.mapper.user.ShopOwnerMapper;
import com.example.userservice.repository.mapper.connect.ShopOwnerToUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShopOwnerService {
    @Autowired
    private ShopOwnerRepository shopOwnerRepository;

    @Autowired
    private ShopOwnerMapper shopOwnerMapper;

    @Autowired
    private ShopOwnerToUserMapper shopOwnerToUserMapper;

    @Autowired
    private UserService userService;

    @Transactional
    public String registerNewUser(ShopOwner shopOwner, User user, UserBasicInfo userBasicInfo, UserAuthentication userAuthentication, PersonalAccount personalAccount) throws RuntimeException{
        if (shopOwner == null || user == null || userBasicInfo == null || userAuthentication == null || personalAccount == null){
            throw new RuntimeException(); //if args are null, exception
        }
        String ret;
        try {
            ret = shopOwnerRepository.insertShopOwner(shopOwner,user,userBasicInfo,userAuthentication,personalAccount);
        }catch (RuntimeException e) {
            throw e;
        }
        if (!ret.equals("success")){
            throw new RuntimeException();
        }
        return ret;
    }

    public ShopOwner findShopOwner(String idCardNumber){
        User user = userService.findUser(idCardNumber);
        ShopOwner shopOwner = shopOwnerRepository.selectShopOwner(user);
        shopOwner.setUser(user);
        return shopOwner;
    }
}
