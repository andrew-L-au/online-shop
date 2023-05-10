package com.example.userservice.repository.mapper.user.connect;

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

    default UserToUserBasicInfo findOneByUser(String userId){
        return this.selectOne(new QueryWrapper<UserToUserBasicInfo>().eq("user_id", userId));
    }

    default UserToUserBasicInfo selectUserByUserBasicInfo(String userBasicInfoId){
        return this.selectOne(new QueryWrapper<UserToUserBasicInfo>().eq("user_basic_info_id", userBasicInfoId));
    }

    default String selectUserBasicInfoByUser(String userId){
        if (userId == null){
            return null;
        }
        UserToUserBasicInfo userToUserBasicInfo = this.selectOne(new QueryWrapper<UserToUserBasicInfo>().eq("user_id", userId));
        if (userToUserBasicInfo == null){
            return null;
        }
        return userToUserBasicInfo.getUserBasicInfoId();
    }
}
