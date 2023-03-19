package com.example.userservice.repository;

import com.example.userservice.model.connect.UserToUserAuthentication;
import com.example.userservice.model.connect.UserToUserBasicInfo;
import com.example.userservice.model.user.User;
import com.example.userservice.model.user.info.auth.UserAuthentication;
import com.example.userservice.model.user.info.basic.UserBasicInfo;
import com.example.userservice.repository.mapper.UserAuthenticationMapper;
import com.example.userservice.repository.mapper.UserBasicInfoMapper;
import com.example.userservice.repository.mapper.connect.UserToUserAuthenticationMapper;
import com.example.userservice.repository.mapper.connect.UserToUserBasicInfoMapper;
import com.example.userservice.repository.mapper.user.UserMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Data
public class UserRepository {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserBasicInfoMapper userBasicInfoMapper;

    @Autowired
    private UserToUserAuthenticationMapper userToUserAuthenticationMapper;

    @Autowired
    private UserToUserBasicInfoMapper userToUserBasicInfoMapper;

    @Autowired
    private UserAuthenticationMapper userAuthenticationMapper;

    public User selectUser(UserAuthentication userAuthentication){
        userAuthentication = userAuthenticationMapper.findOneByUserAuthentication(userAuthentication);
        return selectUser(userAuthentication.getUserAuthenticationId());
    }

    public User selectUser(Long userAuthenticationId){
        UserToUserAuthentication userToUserAuthentication = userToUserAuthenticationMapper.findOneByUserAuthentication(userAuthenticationId);
        return userMapper.findOneByUserToUserAuthentication(userToUserAuthentication);
    }

    public User selectUser(UserBasicInfo userBasicInfo){
        UserToUserBasicInfo userToUserBasicInfo = userToUserBasicInfoMapper.selectUserByUserBasicInfo(userBasicInfo.getUserBasicInfoId());
        return userMapper.selectById(userToUserBasicInfo.getUserId());
    }

    public UserBasicInfo selectUserBasicInfo(Long userId){
        UserToUserBasicInfo userToUserBasicInfo = userToUserBasicInfoMapper.findOneByUser(userId);
        return userBasicInfoMapper.selectById(userToUserBasicInfo.getUserBasicInfoId());
    }


}
