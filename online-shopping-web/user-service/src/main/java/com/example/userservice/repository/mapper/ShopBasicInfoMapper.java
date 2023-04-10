package com.example.userservice.repository.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.shop.ShopBasicInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopBasicInfoMapper extends BaseMapper<ShopBasicInfo> {
    default ShopBasicInfo selectShopBasicInfo(String name){
        if (name == null){
            return null;
        }
        return this.selectOne(new QueryWrapper<ShopBasicInfo>().eq("name",name));
    }


}
