package com.example.userservice.service;

import com.example.userservice.model.account.*;
import com.example.userservice.model.account.connect.AccountToPersonalAccount;
import com.example.userservice.model.account.connect.AccountToShopAccount;
import com.example.userservice.model.account.connect.ShopToAccount;
import com.example.userservice.model.account.connect.UserToAccount;
import com.example.userservice.model.shop.Shop;
import com.example.userservice.model.user.User;
import com.example.userservice.repository.mapper.account.*;
import com.example.userservice.repository.mapper.account.connect.AccountToPersonalAccountMapper;
import com.example.userservice.repository.mapper.account.connect.AccountToShopAccountMapper;
import com.example.userservice.repository.mapper.account.connect.ShopToAccountMapper;
import com.example.userservice.repository.mapper.shop.ShopMapper;
import com.example.userservice.repository.mapper.user.UserMapper;
import com.example.userservice.repository.mapper.user.connect.UserToAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private PersonalAccountMapper personalAccountMapper;

    @Autowired
    private ShopAccountMapper shopAccountMapper;

    @Autowired
    private AccountToPersonalAccountMapper accountToPersonalAccountMapper;

    @Autowired
    private AccountToShopAccountMapper accountToShopAccountMapper;

    @Autowired
    private UserToAccountMapper userToAccountMapper;

    @Autowired
    private ShopToAccountMapper shopToAccountMapper;

    @Transactional
    public Boolean openPersonalAccount(String userId){
        if (userId == null){
            return false;
        }
        if (userToAccountMapper.selectAccountByUser(userId) != null){
            return false;
        }
        User user = userMapper.selectById(userId);
        if (user == null){
            return false;
        }
        Account account = new Account();
        account.setBalance((double) 0);
        accountMapper.insert(account);
        if (account.getAccountId() == null){
            throw new RuntimeException();
        }
        UserToAccount userToAccount = new UserToAccount();
        userToAccount.setUserId(user.getUserId());
        userToAccount.setAccountId(account.getAccountId());
        userToAccountMapper.insert(userToAccount);
        if (userToAccount.getId() == null){
            return false;
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
    public Boolean openShopAccount(String shopId){
        if (shopId == null){
            return false;
        }
        if (shopToAccountMapper.selectAccountByShop(shopId) != null){
            return false;
        }
        Shop shop = shopMapper.selectById(shopId);
        if (shop == null){
            return false;
        }
        Account account = new Account();
        account.setBalance((double) 0);
        accountMapper.insert(account);
        if (account.getAccountId() == null){
            throw new RuntimeException();
        }
        ShopToAccount shopToAccount = new ShopToAccount();
        shopToAccount.setShopId(shop.getShopId());
        shopToAccount.setAccountId(account.getAccountId());
        shopToAccountMapper.insert(shopToAccount);
        if (shopToAccount.getId() == null){
            return false;
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
    public Boolean closeShopAccount(String shopId){
        if (shopId == null){
            return false;
        }
        if (shopToAccountMapper.selectAccountByShop(shopId) == null){
            return false;
        }
        String accountId = shopToAccountMapper.selectAccountByShop(shopId);
        if (accountId == null){
            return false;
        }
        String shopToAccountId = shopToAccountMapper.selectIdByShopAndAccount(shopId,accountId);
        if (shopToAccountId == null){
            return false;
        }
        String shopAccountId = accountToShopAccountMapper.selectShopAccountByAccount(accountId);
        if (shopAccountId == null){
            return false;
        }
        String accountToShopAccountId = accountToShopAccountMapper.selectIdByAccountAndShopAccount(accountId,shopAccountId);
        int num = accountMapper.deleteById(accountId);
        if (num <= 0){
            throw new RuntimeException();
        }
        num = shopToAccountMapper.deleteById(shopToAccountId);
        if (num <= 0){
            throw new RuntimeException();
        }
        num = shopAccountMapper.deleteById(shopAccountId);
        if (num <= 0){
            throw new RuntimeException();
        }
        num = accountToShopAccountMapper.deleteById(accountToShopAccountId);
        if (num <= 0){
            throw new RuntimeException();
        }
        return true;
    }

    @Transactional
    public Boolean redeemAccount(String accountId,Double amount){
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
    public Boolean chargeAccount(String accountId,Double amount){
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
    public Boolean transferFromTo(String fromAccountId,String toAccountId,Double amount){
        if (fromAccountId == null || toAccountId == null || amount == null || amount <= 0){
            return false;
        }
        if (!chargeAccount(fromAccountId, amount)){
            return false;
        }
        if (!redeemAccount(toAccountId, amount)){
            return false;
        }
        return true;
    }

    @Transactional
    public Account accountOfUser(String userId){
        if (userId == null){
            return null;
        }
        String accountId = userToAccountMapper.selectAccountByUser(userId);
        if (accountId == null){
            return null;
        }
        Account account = accountMapper.selectById(accountId);
        return account;
    }

    public Account accountOfShop(String shopId) {
        if (shopId == null){
            return null;
        }
        String accountId = shopToAccountMapper.selectAccountByShop(shopId);
        if (accountId == null){
            return null;
        }
        Account account = accountMapper.selectById(accountId);
        return account;
    }

    public Account middleAccount() {
        String accountId = MiddleAccount.MIDDLE_ACCOUNT_ID;
        if (accountId == null){
            return null;
        }
        Account account = accountMapper.selectById(accountId);
        return account;
    }

    public Account profitAccount() {
        String accountId = ProfitAccount.PROFIT_ACCOUNT_ID;
        if (accountId == null){
            return null;
        }
        Account account = accountMapper.selectById(accountId);
        return account;
    }
}
