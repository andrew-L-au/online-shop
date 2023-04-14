package com.example.userservice.repository.mapper.merchandise;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.merchandise.NewMerchandiseRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewMerchandiseRequestMapper extends BaseMapper<NewMerchandiseRequest> {
    default List<NewMerchandiseRequest> allNewMerchandiseRequests(){
        return this.selectList(new QueryWrapper<>());
    }
}
