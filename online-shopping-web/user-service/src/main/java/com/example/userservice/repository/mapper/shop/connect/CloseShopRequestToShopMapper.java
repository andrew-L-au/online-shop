package com.example.userservice.repository.mapper.shop.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.shop.connect.CloseShopRequestToShop;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CloseShopRequestToShopMapper extends BaseMapper<CloseShopRequestToShop> {
    default String selectShopByCloseShopRequest(String closeShopRequestId){
        if (closeShopRequestId == null){
            return null;
        }
        CloseShopRequestToShop closeShopRequestToShop = this.selectOne(new QueryWrapper<CloseShopRequestToShop>().eq("close_shop_request_id" ,closeShopRequestId));
        if (closeShopRequestToShop == null){
            return null;
        }
        return closeShopRequestToShop.getShopId();
    }

    default String selectCloseShopRequestByShop(String shopId){
        if (shopId == null){
            return null;
        }
        CloseShopRequestToShop closeShopRequestToShop = this.selectOne(new QueryWrapper<CloseShopRequestToShop>().eq("shop_id" ,shopId));
        if (closeShopRequestToShop == null){
            return null;
        }
        return closeShopRequestToShop.getCloseShopRequestId();
    }
}
