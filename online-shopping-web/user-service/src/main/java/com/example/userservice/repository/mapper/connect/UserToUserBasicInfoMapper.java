package com.example.userservice.repository.mapper.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.connect.UserToUserBasicInfo;
import com.example.userservice.model.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserToUserBasicInfoMapper extends BaseMapper<UserToUserBasicInfo> {
    default UserToUserBasicInfo findOneByUser(User user){
        return this.selectOne(new QueryWrapper<UserToUserBasicInfo>().eq("user_id", user.getUserId()));
    }

    default UserToUserBasicInfo findOneByUser(Long userId){
        return this.selectOne(new QueryWrapper<UserToUserBasicInfo>().eq("user_id", userId));
    }

    default UserToUserBasicInfo selectUserByUserBasicInfo(Long userBasicInfoId){
        return this.selectOne(new QueryWrapper<UserToUserBasicInfo>().eq("user_basic_info_id", userBasicInfoId));
    }
}
