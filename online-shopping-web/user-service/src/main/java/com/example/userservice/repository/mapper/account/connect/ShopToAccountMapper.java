package com.example.userservice.repository.mapper.account.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.account.connect.ShopToAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopToAccountMapper extends BaseMapper<ShopToAccount> {
    default Long selectAccountByShop(Long shopId){
        if (shopId == null){
            return null;
        }
        ShopToAccount shopToAccount = this.selectOne(new QueryWrapper<ShopToAccount>().eq("shop_id",shopId));
        if (shopToAccount == null){
            return null;
        }
        return shopToAccount.getAccountId();
    }
}
