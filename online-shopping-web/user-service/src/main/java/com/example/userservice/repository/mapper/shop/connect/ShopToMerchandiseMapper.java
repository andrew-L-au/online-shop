package com.example.userservice.repository.mapper.shop.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.shop.connect.ShopToMerchandise;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ShopToMerchandiseMapper extends BaseMapper<ShopToMerchandise> {
    default List<String> selectMerchandisesByShop(String shopId){
        List<String> merchandisesIds = new ArrayList<>();
        if (shopId == null){
            return null;
        }
        List<ShopToMerchandise> shopToMerchandises = this.selectList(new QueryWrapper<ShopToMerchandise>().eq("shop_id",shopId));
        if (shopToMerchandises == null){
            return null;
        }
        for (ShopToMerchandise shopToMerchandise : shopToMerchandises){
            merchandisesIds.add(shopToMerchandise.getMerchandiseId());
        }
        return merchandisesIds;
    }

    default String selectIdByMerchandise(String merchandiseId){
        if (merchandiseId == null){
            return null;
        }
        ShopToMerchandise shopToMerchandise = this.selectOne(new QueryWrapper<ShopToMerchandise>().eq("merchandise_id",merchandiseId));
        if (shopToMerchandise == null){
            return null;
        }
        return shopToMerchandise.getId();
    }
}
