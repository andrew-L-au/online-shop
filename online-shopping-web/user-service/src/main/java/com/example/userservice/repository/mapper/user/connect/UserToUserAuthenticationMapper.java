package com.example.userservice.repository.mapper.user.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.connect.UserToUserAuthentication;
import com.example.userservice.model.connect.UserToUserBasicInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserToUserAuthenticationMapper extends BaseMapper<UserToUserAuthentication> {
    default UserToUserAuthentication findOneByUserAuthentication(String userAuthenticationId){
        return this.selectOne(new QueryWrapper<UserToUserAuthentication>().eq("user_authentication_id", userAuthenticationId));
    }

    default String findUserAuthenticationByUser(String userId){
        if (userId == null){
            return null;
        }
        UserToUserAuthentication userToUserAuthentication = this.selectOne(new QueryWrapper<UserToUserAuthentication>().eq("user_id", userId));
        if (userToUserAuthentication == null){
            return null;
        }
        return userToUserAuthentication.getUserAuthenticationId();
    }

    default String selectUserAuthenticationByUser(String userId){
        if (userId == null){
            return null;
        }
        UserToUserAuthentication userToUserAuthentication = this.selectOne(new QueryWrapper<UserToUserAuthentication>().eq("user_id", userId));
        if (userToUserAuthentication == null){
            return null;
        }
        return userToUserAuthentication.getUserAuthenticationId();
    }
}
