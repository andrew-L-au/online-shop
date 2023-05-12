package com.example.userservice.repository.mapper.account.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.account.connect.AccountToShopAccount;
import com.example.userservice.model.account.connect.ShopToAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountToShopAccountMapper extends BaseMapper<AccountToShopAccount> {
    default String selectShopAccountByAccount(String accountId){
        if (accountId == null){
            return null;
        }
        AccountToShopAccount accountToShopAccount = this.selectOne(new QueryWrapper<AccountToShopAccount>().eq("account_id", accountId));
        if (accountToShopAccount == null){
            return null;
        }
        return accountToShopAccount.getShopAccountId();
    }

    default String selectIdByAccountAndShopAccount(String accountId, String shopAccountId){
        if (accountId == null || shopAccountId == null){
            return null;
        }
        AccountToShopAccount accountToShopAccount = this.selectOne(new QueryWrapper<AccountToShopAccount>().eq("account_id",accountId).eq("shop_account_id",shopAccountId));
        if (accountToShopAccount == null){
            return null;
        }
        return accountToShopAccount.getId();
    }
}
