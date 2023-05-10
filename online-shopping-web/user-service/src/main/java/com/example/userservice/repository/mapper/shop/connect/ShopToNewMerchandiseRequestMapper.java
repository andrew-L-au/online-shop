package com.example.userservice.repository.mapper.shop.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.shop.connect.ShopToNewMerchandiseRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ShopToNewMerchandiseRequestMapper extends BaseMapper<ShopToNewMerchandiseRequest> {
    default List<String> selectNewMerchandiseRequestsByShop(String shopId){
        List<String> newMerchandiseRequestIds = new ArrayList<>();
        if (shopId == null){
            return null;
        }
        List<ShopToNewMerchandiseRequest> shopToNewMerchandiseRequests = this.selectList(new QueryWrapper<ShopToNewMerchandiseRequest>().eq("shop_id",shopId));
        if (shopToNewMerchandiseRequests == null){
            return null;
        }
        for (ShopToNewMerchandiseRequest shopToNewMerchandiseRequest : shopToNewMerchandiseRequests){
            newMerchandiseRequestIds.add(shopToNewMerchandiseRequest.getNewMerchandiseRequestId());
        }
        return newMerchandiseRequestIds;
    }

    default String selectShopByNewMerchandiseRequest(String newMerchandiseRequestId){
        if (newMerchandiseRequestId == null){
            return null;
        }
        ShopToNewMerchandiseRequest shopToNewMerchandiseRequest = this.selectOne(new QueryWrapper<ShopToNewMerchandiseRequest>().eq("new_merchandise_request_id",newMerchandiseRequestId));
        if (shopToNewMerchandiseRequest == null){
            return null;
        }
        return shopToNewMerchandiseRequest.getShopId();
    }
}
