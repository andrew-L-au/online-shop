package com.example.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.userservice.model.connect.UserToUserAuthentication;
import com.example.userservice.model.connect.UserToUserBasicInfo;
import com.example.userservice.model.user.User;
import com.example.userservice.model.user.info.auth.UserAuthentication;
import com.example.userservice.model.user.info.basic.UserBasicInfo;
import com.example.userservice.repository.*;
import com.example.userservice.repository.mapper.UserAuthenticationMapper;
import com.example.userservice.repository.mapper.UserBasicInfoMapper;
import com.example.userservice.repository.mapper.connect.UserToUserAuthenticationMapper;
import com.example.userservice.repository.mapper.connect.UserToUserBasicInfoMapper;
import com.example.userservice.repository.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper,User> implements IService<User> {
    @Autowired
    UserToUserAuthenticationMapper userToUserAuthenticationMapper;

    @Autowired
    UserToUserBasicInfoMapper userToUserBasicInfoMapper;

    @Autowired
    UserBasicInfoMapper userBasicInfoMapper;

    @Autowired
    UserAuthenticationMapper userAuthenticationMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserBasicInfoRepository userBasicInfoRepository;

    public boolean existOneInfoWithSameUsername(String username){
        return userBasicInfoRepository.selectUserBasicInfoByUsername(username) != null;
    }

    public boolean existOneInfoWithSamePhoneNumber(String phoneNumber){
        return userBasicInfoRepository.selectUserBasicInfoByPhoneNumber(phoneNumber) != null;
    }

    public boolean existOneInfoWithSameIdCardNumber(String idCardNumber){
        return userBasicInfoRepository.selectUserBasicInfoByIdCardNumber(idCardNumber) != null;
    }

    public boolean existOneInfoWithSameEmail(String email){
        return userBasicInfoRepository.selectUserBasicInfoByEmail(email) != null;
    }

    public boolean existUserAuthentication(UserAuthentication userAuthentication){
        return userAuthenticationMapper.findOneByUserAuthentication(userAuthentication) != null;
    }

    public void registerNewUser(User user){
        userBasicInfoMapper.insert(user.getUserBasicInfo());
        userAuthenticationMapper.insert(user.getUserAuthentication());
        userMapper.insert(user);
        userToUserBasicInfoMapper.insert(new UserToUserBasicInfo(null ,user.getUserId(),user.getUserBasicInfo().getUserBasicInfoId()));
        userToUserAuthenticationMapper.insert(new UserToUserAuthentication(null, user.getUserId(),user.getUserAuthentication().getUserAuthenticationId()));
    }

    public User findUser(UserAuthentication userAuthentication){
        return userRepository.selectUser(userAuthentication);
    }

    public UserBasicInfo findUserBasicInfo(Long userId){
        return userRepository.selectUserBasicInfo(userId);
    }

    public User findUser(String idCardNumber){
        UserBasicInfo userBasicInfo = userBasicInfoRepository.selectUserBasicInfoByIdCardNumber(idCardNumber);
        return userRepository.selectUser(userBasicInfo);
    }

}
