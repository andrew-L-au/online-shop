package com.example.userservice.repository.mapper.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.connect.ShopToShopBasicInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopToShopBasicInfoMapper extends BaseMapper<ShopToShopBasicInfo> {
    default Long selectShopBasicInfoByShop(Long shopId){
        ShopToShopBasicInfo shopToShopBasicInfo = this.selectOne(new QueryWrapper<ShopToShopBasicInfo>().eq("shop_id",shopId));
        if (shopToShopBasicInfo == null){
            return null;
        }
        return shopToShopBasicInfo.getShopBasicInfoId();
    }

    default Long selectShopByShopBasicInfo(Long shopBasicInfoId){
        ShopToShopBasicInfo shopToShopBasicInfo = this.selectOne(new QueryWrapper<ShopToShopBasicInfo>().eq("shop_basic_info_id",shopBasicInfoId));
        if (shopToShopBasicInfo == null){
            return null;
        }
        return shopToShopBasicInfo.getShopId();
    }
}
