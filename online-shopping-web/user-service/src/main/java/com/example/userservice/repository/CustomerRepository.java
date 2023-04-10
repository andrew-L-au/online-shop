package com.example.userservice.repository;

import com.example.userservice.model.Account.PersonalAccount;
import com.example.userservice.model.connect.CustomerToUser;
import com.example.userservice.model.user.Customer;
import com.example.userservice.model.user.User;
import com.example.userservice.model.user.info.auth.UserAuthentication;
import com.example.userservice.model.user.info.auth.UserRole;
import com.example.userservice.model.user.info.basic.UserBasicInfo;
import com.example.userservice.repository.mapper.connect.CustomerToUserMapper;
import com.example.userservice.repository.mapper.user.CustomerMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Data
public class CustomerRepository {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerToUserMapper customerToUserMapper;

    public String insertCustomer(Customer customer, User user, UserBasicInfo userBasicInfo, UserAuthentication userAuthentication, PersonalAccount personalAccount){
        if (customer == null || user == null || userBasicInfo == null || userAuthentication == null){
            return "fail"; //if args are null, exception
        }
        user.setUserId(null);
        user.setUserRole(UserRole.CUSTOMER); //prepare user object, need to clear id (if it has)
        Boolean ret = userRepository.insertUser(user,userBasicInfo,userAuthentication); //insert user object, maybe has error
        if (!ret){
            return "fail";
        }
        customer.setCustomerId(null); // prepare customer object
        customerMapper.insert(customer); //insert customer object, maybe has error
        if (customer.getCustomerId() == null){
            return "fail";
        }
        CustomerToUser customerToUser = new CustomerToUser();
        customerToUser.setUserId(user.getUserId());
        customerToUser.setCustomerId(customer.getCustomerId()); // prepare connection object
        customerToUserMapper.insert(customerToUser);  //insert connection object, maybe has error
        if (customerToUser.getCustomerId() == null){
            return "fail";
        }
        return "success";
    }
}
