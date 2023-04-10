package com.example.userservice.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.shop.Shop;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopMapper extends BaseMapper<Shop> {
}
