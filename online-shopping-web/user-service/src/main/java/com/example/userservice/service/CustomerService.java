package com.example.userservice.service;

import com.example.userservice.model.connect.CustomerToUser;
import com.example.userservice.model.user.Customer;
import com.example.userservice.repository.mapper.user.CustomerMapper;
import com.example.userservice.repository.mapper.connect.CustomerToUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends UserService{

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    CustomerToUserMapper customerToUserMapper;

    public boolean registerNewUser(Customer customer){
        this.registerNewUser(customer.getUser());
        customerMapper.insert(customer);
        CustomerToUser customerToUser = new CustomerToUser();
        customerToUser.setUserId(customer.getUser().getUserId());
        customerToUser.setCustomerId(customer.getCustomerId());
        customerToUserMapper.insert(customerToUser);
        return true;
    }
}
