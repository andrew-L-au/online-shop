package com.example.userservice.repository.mapper.shop.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.shop.connect.ShopToModifyMerchandiseRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ShopToModifyMerchandiseRequestMapper extends BaseMapper<ShopToModifyMerchandiseRequest> {
    default List<String> selectModifyMerchandiseRequestsByShop(String shopId){
        List<String> modifyMerchandiseRequestIds = new ArrayList<>();
        if (shopId == null){
            return null;
        }
        List<ShopToModifyMerchandiseRequest> shopToModifyMerchandiseRequests = this.selectList(new QueryWrapper<ShopToModifyMerchandiseRequest>().eq("shop_id",shopId));
        if (shopToModifyMerchandiseRequests == null){
            return null;
        }
        for (ShopToModifyMerchandiseRequest shopToModifyMerchandiseRequest : shopToModifyMerchandiseRequests){
            modifyMerchandiseRequestIds.add(shopToModifyMerchandiseRequest.getModifyMerchandiseRequestId());
        }
        return modifyMerchandiseRequestIds;
    }

    default String selectShopByModifyMerchandiseRequest(String modifyMerchandiseRequestId){
        if (modifyMerchandiseRequestId == null){
            return null;
        }
        ShopToModifyMerchandiseRequest shopToModifyMerchandiseRequest = this.selectOne(new QueryWrapper<ShopToModifyMerchandiseRequest>().eq("modify_merchandise_request_id",modifyMerchandiseRequestId));
        if (shopToModifyMerchandiseRequest == null){
            return null;
        }
        return shopToModifyMerchandiseRequest.getShopId();
    }
}
