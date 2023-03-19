package com.example.userservice.repository.mapper.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.connect.UserToUserAuthentication;
import com.example.userservice.model.user.info.auth.UserAuthentication;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserToUserAuthenticationMapper extends BaseMapper<UserToUserAuthentication> {
    default UserToUserAuthentication findOneByUserAuthentication(UserAuthentication userAuthentication){
        return this.selectOne(new QueryWrapper<UserToUserAuthentication>().eq("user_authentication_id", userAuthentication.getUserAuthenticationId()));
    }

    default UserToUserAuthentication findOneByUserAuthentication(Long userAuthenticationId){
        return this.selectOne(new QueryWrapper<UserToUserAuthentication>().eq("user_authentication_id", userAuthenticationId));
    }
}
