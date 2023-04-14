package com.example.userservice.repository.mapper.shop.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.shop.connect.ShopToMerchandise;
import com.example.userservice.model.shop.connect.ShopToNewMerchandise;

import java.util.ArrayList;
import java.util.List;

public interface ShopToNewMerchandiseMapper extends BaseMapper<ShopToNewMerchandise> {
    default List<Long> selectNewMerchandisesByShop(Long shopId){
        List<Long> newMerchandisesIds = new ArrayList<>();
        if (shopId == null){
            return null;
        }
        List<ShopToNewMerchandise> shopToNewMerchandises = this.selectList(new QueryWrapper<ShopToNewMerchandise>().eq("shop_id",shopId));
        if (shopToNewMerchandises == null){
            return null;
        }
        for (ShopToNewMerchandise shopToNewMerchandise : shopToNewMerchandises){
            newMerchandisesIds.add(shopToNewMerchandise.getNewMerchandiseId());
        }
        return newMerchandisesIds;
    }
}
