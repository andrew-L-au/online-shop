package com.example.shopservice.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import model.shop.Shop;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopMapper extends BaseMapper<Shop> {
}
