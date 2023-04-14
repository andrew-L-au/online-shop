package com.example.userservice.repository.mapper.account.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.account.connect.UserToAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserToAccountMapper extends BaseMapper<UserToAccount> {
    default Long selectAccountByUser(Long userId){
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
