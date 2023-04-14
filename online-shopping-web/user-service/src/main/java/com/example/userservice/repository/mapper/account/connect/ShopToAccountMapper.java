package com.example.userservice.repository.mapper.account.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.account.connect.ShopToShopAccount;
import com.example.userservice.model.account.connect.UserToPersonalAccount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface ShopToShopAccountMapper extends BaseMapper<ShopToShopAccount> {
    default Long selectShopAccountByShop(Long shopId){
        if (shopId == null){
            return null;
        }
        ShopToShopAccount shopToShopAccount = this.selectOne(new QueryWrapper<ShopToShopAccount>().eq("shop_id",shopId));
        if (shopToShopAccount == null){
            return null;
        }
        return shopToShopAccount.getShopAccountId();
    }
}
