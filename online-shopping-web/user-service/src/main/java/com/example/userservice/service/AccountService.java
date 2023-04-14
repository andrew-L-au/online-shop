package com.example.userservice.service;

import com.example.userservice.model.account.*;
import com.example.userservice.model.account.connect.AccountToPersonalAccount;
import com.example.userservice.model.account.connect.AccountToShopAccount;
import com.example.userservice.repository.mapper.account.*;
import com.example.userservice.repository.mapper.account.connect.AccountToPersonalAccountMapper;
import com.example.userservice.repository.mapper.account.connect.AccountToShopAccountMapper;
import com.example.userservice.repository.mapper.account.connect.ShopToAccountMapper;
import com.example.userservice.repository.mapper.user.connect.UserToAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private PersonalAccountMapper personalAccountMapper;

    @Autowired
    private ShopAccountMapper shopAccountMapper;

    @Autowired
    private MiddleAccountMapper middleAccountMapper;

    @Autowired
    private ProfitAccountMapper profitAccountMapper;

    @Autowired
    private AccountToPersonalAccountMapper accountToPersonalAccountMapper;

    @Autowired
    private AccountToShopAccountMapper accountToShopAccountMapper;

    @Autowired
    private UserToAccountMapper userToAccountMapper;

    @Autowired
    private ShopToAccountMapper shopToAccountMapper;

    @Transactional
    public Boolean openPersonalAccount(Long userId){
        if (userId == null){
            return false;
        }
        if (userToAccountMapper.selectAccountByUser(userId) != null){
            return false;
        }
        Account account = new Account();
        account.setBalance((double) 0);
        accountMapper.insert(account);
        if (account.getAccountId() == null){
            throw new RuntimeException();
        }
        PersonalAccount personalAccount = new PersonalAccount();
        personalAccountMapper.insert(personalAccount);
        if (personalAccount.getPersonalAccountId() == null){
            throw new RuntimeException();
        }
        AccountToPersonalAccount accountToPersonalAccount = new AccountToPersonalAccount(null, account.getAccountId(), personalAccount.getPersonalAccountId());
        accountToPersonalAccountMapper.insert(accountToPersonalAccount);
        if (accountToPersonalAccount.getId() == null){
            throw new RuntimeException();
        }
        return true;
    }

    @Transactional
    public Boolean openShopAccount(Long shopId){
        if (shopId == null){
            return false;
        }
        if (shopToAccountMapper.selectAccountByShop(shopId) != null){
            return false;
        }
        Account account = new Account();
        account.setBalance((double) 0);
        accountMapper.insert(account);
        if (account.getAccountId() == null){
            throw new RuntimeException();
        }
        ShopAccount shopAccount = new ShopAccount();
        shopAccountMapper.insert(shopAccount);
        if (shopAccount.getShopAccountId() == null){
            throw new RuntimeException();
        }
        AccountToShopAccount accountToShopAccount = new AccountToShopAccount(null, account.getAccountId(), shopAccount.getShopAccountId());
        accountToShopAccountMapper.insert(accountToShopAccount);
        if (accountToShopAccount.getId() == null){
            throw new RuntimeException();
        }
        return true;
    }

    @Transactional
    public Boolean redeemAccount(Long accountId,Double amount){
        if (accountId == null || amount == null || amount <= 0){
            return false;
        }
        Account account = accountMapper.selectById(accountId);
        if (account == null){
            return false;
        }
        account.setBalance(account.getBalance() + amount);
        int rowNum = accountMapper.updateById(account);
        if (rowNum <= 0){
            throw new RuntimeException();
        }
        return true;
    }

    @Transactional
    public Boolean chargeAccount(Long accountId,Double amount){
        if (accountId == null || amount == null || amount <= 0){
            return false;
        }
        Account account = accountMapper.selectById(accountId);
        if (account == null){
            return false;
        }
        account.setBalance(account.getBalance() - amount);
        int rowNum = accountMapper.updateById(account);
        if (rowNum <= 0){
            throw new RuntimeException();
        }
        return true;
    }

    @Transactional
    public Boolean transferFromTo(Long fromAccountId,Long toAccountId,Double amount){
        if (fromAccountId == null || toAccountId == null || amount == null || amount <= 0){
            return false;
        }
        if (chargeAccount(fromAccountId, amount)){
            return false;
        }
        if (redeemAccount(toAccountId, amount)){
            return false;
        }
        return true;
    }
}
