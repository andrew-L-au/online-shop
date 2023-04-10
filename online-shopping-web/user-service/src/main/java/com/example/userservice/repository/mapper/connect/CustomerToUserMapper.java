package com.example.userservice.repository.mapper.connect;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.connect.CustomerToUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerToUserMapper extends BaseMapper<CustomerToUser> {
}
