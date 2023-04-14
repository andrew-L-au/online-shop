package com.example.userservice.repository.mapper.shop;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.shop.connect.OpenShopRequestToShop;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OpenShopRequestToShopMapper extends BaseMapper<OpenShopRequestToShop> {
    default Long selectOpenShopRequestByShop(Long shopId) {
        OpenShopRequestToShop openShopRequestToShop = this.selectOne(new QueryWrapper<OpenShopRequestToShop>().eq("shop_id", shopId));
        if (openShopRequestToShop == null){
            return null;
        }
        return openShopRequestToShop.getOpenShopRequestId();
    }

    default Long selectShopByOpenShopRequest(Long openShopRequestId){
        OpenShopRequestToShop openShopRequestToShop = this.selectOne(new QueryWrapper<OpenShopRequestToShop>().eq("open_shop_request_id", openShopRequestId));
        if (openShopRequestToShop == null){
            return null;
        }
        return openShopRequestToShop.getShopId();
    }
}
