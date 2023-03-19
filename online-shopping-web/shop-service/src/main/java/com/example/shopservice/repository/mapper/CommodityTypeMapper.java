package com.example.shopservice.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import model.shop.CommodityType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommodityTypeMapper extends BaseMapper<CommodityType> {
}
