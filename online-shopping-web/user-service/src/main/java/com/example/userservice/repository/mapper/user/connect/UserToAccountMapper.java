package com.example.userservice.repository.mapper.user.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.account.connect.UserToAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserToAccountMapper extends BaseMapper<UserToAccount> {
    default String selectAccountByUser(String userId){
        if (userId == null){
            return null;
        }
        UserToAccount userToAccount = this.selectOne(new QueryWrapper<UserToAccount>().eq("user_id",userId));
        if (userToAccount == null){
            return null;
        }
        return userToAccount.getAccountId();
    }
}
