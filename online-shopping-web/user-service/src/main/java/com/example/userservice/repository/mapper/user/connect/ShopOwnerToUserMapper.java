package com.example.userservice.repository.mapper.user.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.connect.ShopOwnerToUser;
import com.example.userservice.model.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopOwnerToUserMapper extends BaseMapper<ShopOwnerToUser> {
    default ShopOwnerToUser selectShopOwnerToUserByUser(User user) throws RuntimeException{
        if (user.getUserId() == null){
            throw new RuntimeException("user id is null");
        }
        return this.selectOne(new QueryWrapper<ShopOwnerToUser>().eq("user_id" , user.getUserId()));
    }

    default String selectShopOwnerByUser(String userId){
        if (userId == null){
            return null;
        }
        ShopOwnerToUser shopOwnerToUser = this.selectOne(new QueryWrapper<ShopOwnerToUser>().eq("user_id" , userId));
        if (shopOwnerToUser == null){
            return null;
        }
        return shopOwnerToUser.getShopOwnerId();
    }

    default String selectUserByShopOwner(String shopOwnerId){
        if (shopOwnerId == null){
            return null;
        }
        ShopOwnerToUser shopOwnerToUser = this.selectOne(new QueryWrapper<ShopOwnerToUser>().eq("shop_owner_id" , shopOwnerId));
        if (shopOwnerToUser == null){
            return null;
        }
        return shopOwnerToUser.getUserId();
    }
}
