package com.example.userservice.repository.mapper.connect;

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
}
