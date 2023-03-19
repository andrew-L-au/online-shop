package com.example.shopservice.repository.mapper.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import model.connect.ShopToShopBasicInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopToShopBasicInfoMapper extends BaseMapper<ShopToShopBasicInfo> {
    default Long selectShopBasicInfoByShop(Long shopId){
        return this.selectOne(new QueryWrapper<ShopToShopBasicInfo>().eq("shop_id",shopId)).getShopBasicInfoId();
    }
}
