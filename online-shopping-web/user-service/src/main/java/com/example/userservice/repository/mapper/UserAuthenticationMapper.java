package com.example.userservice.repository.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.user.info.auth.UserAuthentication;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAuthenticationMapper extends BaseMapper<UserAuthentication> {
    default UserAuthentication findOneByPrincipal(String principal){
        return this.selectOne(new QueryWrapper<UserAuthentication>().eq("principal", principal));
    }

    default UserAuthentication findOneByUserAuthentication(UserAuthentication userAuthentication){
        if (userAuthentication.getPrincipal() == null || userAuthentication.getCredential() == null){
            return null;
        }
        return this.selectOne(new QueryWrapper<UserAuthentication>().eq("principal", userAuthentication.getPrincipal()).eq("credential", userAuthentication.getCredential()));
    }
}
