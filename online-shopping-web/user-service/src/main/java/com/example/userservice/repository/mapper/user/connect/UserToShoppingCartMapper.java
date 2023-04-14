package com.example.userservice.repository.mapper.user.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.shop.connect.CloseShopRequestToShop;
import com.example.userservice.model.user.connect.UserToShoppingCart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserToShoppingCartMapper extends BaseMapper<UserToShoppingCart> {
    default Long selectShoppingCartByUser(Long userId){
        if (userId == null){
            return null;
        }
        UserToShoppingCart userToShoppingCart = this.selectOne(new QueryWrapper<UserToShoppingCart>().eq("user_id" ,userId));
        if (userToShoppingCart == null){
            return null;
        }
        return userToShoppingCart.getShoppingCartId();
    }
}
