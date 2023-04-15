package com.example.userservice.repository.mapper.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.connect.ShopOwnerToShop;
import com.example.userservice.model.connect.ShopOwnerToUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopOwnerToShopMapper extends BaseMapper<ShopOwnerToShop> {
    default Long selectShopByShopOwner(Long shopOwnerId){
        if (shopOwnerId == null){
            return null;
        }
        ShopOwnerToShop shopOwnerToShop = this.selectOne(new QueryWrapper<ShopOwnerToShop>().eq("shop_owner_id" , shopOwnerId));
        if (shopOwnerToShop == null){
            return null;
        }
        return shopOwnerToShop.getShopId();
    }

    default Long selectShopOwnerByShop(Long shopId){
        if (shopId == null){
            return null;
        }
        ShopOwnerToShop shopOwnerToShop = this.selectOne(new QueryWrapper<ShopOwnerToShop>().eq("shop_id" , shopId));
        if (shopOwnerToShop == null){
            return null;
        }
        return shopOwnerToShop.getShopOwnerId();
    }
}
