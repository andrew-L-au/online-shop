package com.example.userservice.service;

import com.example.userservice.model.account.PersonalAccount;
import com.example.userservice.model.connect.CustomerToUser;
import com.example.userservice.model.connect.ShopOwnerToUser;
import com.example.userservice.model.user.ShopOwner;
import com.example.userservice.model.user.User;
import com.example.userservice.model.user.info.auth.UserAuthentication;
import com.example.userservice.model.user.info.auth.UserRole;
import com.example.userservice.model.user.info.basic.UserBasicInfo;
import com.example.userservice.repository.ShopOwnerRepository;
import com.example.userservice.repository.mapper.user.ShopOwnerMapper;
import com.example.userservice.repository.mapper.user.connect.ShopOwnerToUserMapper;
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
    public Boolean registerNewUser(ShopOwner shopOwner, User user, UserBasicInfo userBasicInfo, UserAuthentication userAuthentication, PersonalAccount personalAccount) throws RuntimeException{
        if (shopOwner == null || user == null || userBasicInfo == null || userAuthentication == null || personalAccount == null){
            return false; //if args are null, exception
        }
        user.setUserId(null);
        user.setUserRole(UserRole.SHOP_OWNER); //prepare user object, need to clear id (if it has)
        Boolean ret = userService.registerNewUser(user,userBasicInfo,userAuthentication); //insert user object, maybe has error
        if (!ret || user.getUserId() == null){
            throw new RuntimeException();
        }
        shopOwner.setShopOwnerId(null); // prepare customer object
        shopOwnerMapper.insert(shopOwner); //insert customer object, maybe has error
        if (shopOwner.getShopOwnerId() == null){
            throw new RuntimeException();
        }
        ShopOwnerToUser shopOwnerToUser = new ShopOwnerToUser();
        shopOwnerToUser.setUserId(user.getUserId());
        shopOwnerToUser.setShopOwnerId(shopOwner.getShopOwnerId()); // prepare connection object
        shopOwnerToUserMapper.insert(shopOwnerToUser);  //insert connection object, maybe has error
        if (shopOwnerToUser.getShopOwnerId() == null){
            throw new RuntimeException();
        }
        return true;
    }

    public ShopOwner findShopOwner(String idCardNumber){
        User user = userService.findUser(idCardNumber);
        ShopOwner shopOwner = shopOwnerRepository.selectShopOwner(user);
        shopOwner.setUser(user);
        return shopOwner;
    }
}
