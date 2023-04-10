package com.example.userservice.repository.mapper.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.connect.ShopToCommodityType;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ShopToCommodityTypeMapper extends BaseMapper<ShopToCommodityType> {
    default List<Long> selectCommodityTypeByShop(Long shopId){
        List<ShopToCommodityType> shopToCommodityTypes = this.selectList(new QueryWrapper<ShopToCommodityType>().eq("shop_id" , shopId));
        List<Long> commodityTypeIds = new ArrayList<>();
        shopToCommodityTypes.forEach(e -> {commodityTypeIds.add(e.getCommodityTypeId());});
        return commodityTypeIds;
    }
}
