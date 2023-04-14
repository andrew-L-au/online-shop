package com.example.userservice.service;

import com.example.userservice.model.account.PersonalAccount;
import com.example.userservice.model.user.Customer;
import com.example.userservice.model.user.User;
import com.example.userservice.model.user.info.auth.UserAuthentication;
import com.example.userservice.model.user.info.basic.UserBasicInfo;
import com.example.userservice.repository.CustomerRepository;
import com.example.userservice.repository.mapper.user.CustomerMapper;
import com.example.userservice.repository.mapper.connect.CustomerToUserMapper;
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
    private CustomerRepository customerRepository;

    @Transactional
    public String registerNewUser(Customer customer, User user, UserBasicInfo userBasicInfo, UserAuthentication userAuthentication, PersonalAccount personalAccount) throws RuntimeException{
        if (customer == null || user == null || userBasicInfo == null || userAuthentication == null || personalAccount == null){
            throw new RuntimeException(); //if args are null, exception
        }
        String ret;
        try {
            ret = customerRepository.insertCustomer(customer,user,userBasicInfo,userAuthentication,personalAccount);
        }catch (RuntimeException e) {
            throw e;
        }
        if (!ret.equals("success")){
            throw new RuntimeException();
        }
        return ret;
    }
}
