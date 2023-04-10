package com.example.userservice.repository;

import com.example.userservice.model.Account.PersonalAccount;
import com.example.userservice.model.connect.CustomerToUser;
import com.example.userservice.model.connect.ShopOwnerToUser;
import com.example.userservice.model.user.Customer;
import com.example.userservice.model.user.ShopOwner;
import com.example.userservice.model.user.User;
import com.example.userservice.model.user.info.auth.UserAuthentication;
import com.example.userservice.model.user.info.auth.UserRole;
import com.example.userservice.model.user.info.basic.UserBasicInfo;
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

    @Autowired
    private UserRepository userRepository;

    public ShopOwner selectShopOwner(User user) throws RuntimeException{
        ShopOwnerToUser shopOwnerToUser;
        try {
            shopOwnerToUser = shopOwnerToUserMapper.selectShopOwnerToUserByUser(user);
        }catch (RuntimeException runtimeException){
            throw runtimeException;
        }
        return shopOwnerMapper.selectById(shopOwnerToUser.getShopOwnerId());
    }

    public String insertShopOwner(ShopOwner shopOwner, User user, UserBasicInfo userBasicInfo, UserAuthentication userAuthentication, PersonalAccount personalAccount){
        if (shopOwner == null || user == null || userBasicInfo == null || userAuthentication == null){
            return "fail"; //if args are null, exception
        }
        user.setUserId(null);
        user.setUserRole(UserRole.CUSTOMER); //prepare user object, need to clear id (if it has)
        Boolean ret = userRepository.insertUser(user,userBasicInfo,userAuthentication); //insert user object, maybe has error
        if (!ret){
            return "fail";
        }
        shopOwner.setShopOwnerId(null); // prepare customer object
        shopOwnerMapper.insert(shopOwner); //insert customer object, maybe has error
        if (shopOwner.getShopOwnerId() == null){
            return "fail";
        }
        ShopOwnerToUser shopOwnerToUser = new ShopOwnerToUser();
        shopOwnerToUser.setUserId(user.getUserId());
        shopOwnerToUser.setShopOwnerId(shopOwner.getShopOwnerId()); // prepare connection object
        shopOwnerToUserMapper.insert(shopOwnerToUser);  //insert connection object, maybe has error
        if (shopOwnerToUser.getShopOwnerId() == null){
            return "fail";
        }
        return "success";
    }
}
