package com.example.userservice.repository.mapper.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.connect.UserToUserAuthentication;
import com.example.userservice.model.user.User;
import com.example.userservice.model.user.info.auth.UserAuthentication;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    default User findOneByUserToUserAuthentication(UserToUserAuthentication userToUserAuthentication){
        return this.selectOne(new QueryWrapper<User>().eq("user_id",userToUserAuthentication.getUserId()));
    }

}
