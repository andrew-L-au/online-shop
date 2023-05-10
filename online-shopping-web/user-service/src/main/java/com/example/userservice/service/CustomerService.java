package com.example.userservice.service;

import com.example.userservice.model.account.PersonalAccount;
import com.example.userservice.model.connect.CustomerToUser;
import com.example.userservice.model.user.Customer;
import com.example.userservice.model.user.User;
import com.example.userservice.model.user.info.auth.UserAuthentication;
import com.example.userservice.model.user.info.auth.UserRole;
import com.example.userservice.model.user.info.basic.UserBasicInfo;
import com.example.userservice.repository.mapper.user.CustomerMapper;
import com.example.userservice.repository.mapper.user.connect.CustomerToUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerToUserMapper customerToUserMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private AccountService accountService;

    @Transactional
    public Boolean registerNewUser(Customer customer, User user, UserBasicInfo userBasicInfo, UserAuthentication userAuthentication, PersonalAccount personalAccount) throws RuntimeException{
        if (customer == null || user == null || userBasicInfo == null || userAuthentication == null || personalAccount == null){
            return false; //if args are null, exception
        }
        user.setUserId(null);
        user.setUserRole(UserRole.CUSTOMER); //prepare user object, need to clear id (if it has)
        Boolean ret = userService.registerNewUser(user,userBasicInfo,userAuthentication); //insert user object, maybe has error
        if (!ret || user.getUserId() == null){
            throw new RuntimeException();
        }
        ret = shoppingCartService.creatShoppingCart(user.getUserId());
        if (!ret){
            throw new RuntimeException();
        }
        customer.setCustomerId(null); // prepare customer object
        customerMapper.insert(customer); //insert customer object, maybe has error
        if (customer.getCustomerId() == null){
            throw new RuntimeException();
        }
        CustomerToUser customerToUser = new CustomerToUser();
        customerToUser.setUserId(user.getUserId());
        customerToUser.setCustomerId(customer.getCustomerId()); // prepare connection object
        customerToUserMapper.insert(customerToUser);  //insert connection object, maybe has error
        if (customerToUser.getCustomerId() == null){
            throw new RuntimeException();
        }
        return true;
    }
}
