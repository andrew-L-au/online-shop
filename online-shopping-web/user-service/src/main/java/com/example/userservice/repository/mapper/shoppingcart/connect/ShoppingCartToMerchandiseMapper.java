package com.example.userservice.repository.mapper.shoppingcart.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.merchandise.connect.ModifyMerchandiseRequestToMerchandise;
import com.example.userservice.model.shoppingcart.connect.ShoppingCartToMerchandise;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ShoppingCartToMerchandiseMapper extends BaseMapper<ShoppingCartToMerchandise> {
    default List<Long> selectMerchandisesByShoppingCart(Long shoppingCartId){
        List<Long> merchandiseIds = new ArrayList<>();
        if (shoppingCartId == null){
            return null;
        }
        List<ShoppingCartToMerchandise> shoppingCartToMerchandises = this.selectList(new QueryWrapper<ShoppingCartToMerchandise>().eq("shopping_cart_id",shoppingCartId));
        if (shoppingCartToMerchandises == null){
            return null;
        }
        for (ShoppingCartToMerchandise shoppingCartToMerchandise : shoppingCartToMerchandises){
            if (shoppingCartToMerchandise.getMerchandiseId() == null){
                continue;
            }
            merchandiseIds.add(shoppingCartToMerchandise.getMerchandiseId());
        }
        return merchandiseIds;
    }
}
