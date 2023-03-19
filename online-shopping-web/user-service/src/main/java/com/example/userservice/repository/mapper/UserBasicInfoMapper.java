package com.example.userservice.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.user.info.basic.UserBasicInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserBasicInfoMapper extends BaseMapper<UserBasicInfo> {

}
