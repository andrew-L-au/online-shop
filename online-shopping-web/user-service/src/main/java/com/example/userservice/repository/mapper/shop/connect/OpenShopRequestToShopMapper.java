package com.example.userservice.repository.mapper.shop.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.shop.connect.OpenShopRequestToShop;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OpenShopRequestToShopMapper extends BaseMapper<OpenShopRequestToShop> {
    default String selectOpenShopRequestByShop(String shopId) {
        OpenShopRequestToShop openShopRequestToShop = this.selectOne(new QueryWrapper<OpenShopRequestToShop>().eq("shop_id", shopId));
        if (openShopRequestToShop == null){
            return null;
        }
        return openShopRequestToShop.getOpenShopRequestId();
    }

    default String selectShopByOpenShopRequest(String openShopRequestId){
        OpenShopRequestToShop openShopRequestToShop = this.selectOne(new QueryWrapper<OpenShopRequestToShop>().eq("open_shop_request_id", openShopRequestId));
        if (openShopRequestToShop == null){
            return null;
        }
        return openShopRequestToShop.getShopId();
    }
}
