package com.example.shopservice.repository.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import model.OpenShopRequest;
import model.connect.OpenShopRequestToShop;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OpenShopRequestToShopMapper extends BaseMapper<OpenShopRequestToShop> {
    default Long selectOpenShopRequestByShop() {
        return null;
    }

    default Long selectShopByOpenShopRequest(Long openShopRequestId){
        return this.selectOne(new QueryWrapper<OpenShopRequestToShop>().eq("open_shop_request_id", openShopRequestId)).getShopId();
    }
}
