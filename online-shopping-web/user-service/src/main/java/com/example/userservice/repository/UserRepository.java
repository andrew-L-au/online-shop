package com.example.userservice.repository;

import com.example.userservice.model.connect.UserToUserAuthentication;
import com.example.userservice.model.connect.UserToUserBasicInfo;
import com.example.userservice.model.user.User;
import com.example.userservice.model.user.info.auth.UserAuthentication;
import com.example.userservice.model.user.info.basic.UserBasicInfo;
import com.example.userservice.repository.mapper.user.UserAuthenticationMapper;
import com.example.userservice.repository.mapper.user.UserBasicInfoMapper;
import com.example.userservice.repository.mapper.user.connect.UserToUserAuthenticationMapper;
import com.example.userservice.repository.mapper.user.connect.UserToUserBasicInfoMapper;
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

    public User selectUser(String principal, String credential){
        if (principal == null || credential == null){
            return null;
        }
        UserAuthentication userAuthentication = new UserAuthentication(null,null,principal,credential);
        userAuthentication = userAuthenticationMapper.findOneByUserAuthentication(userAuthentication);
        if (userAuthentication.getUserAuthenticationId() == null){
            return null;
        }
        User user = selectUserByUserAuthentication(userAuthentication.getUserAuthenticationId());
        return user;
    }

    public User selectUser(String principal){
        if (principal == null){
            return null;
        }
        UserAuthentication userAuthentication = userAuthenticationMapper.findOneByPrincipal(principal);
        if (userAuthentication.getUserAuthenticationId() == null){
            return null;
        }
        User user = selectUserByUserAuthentication(userAuthentication.getUserAuthenticationId());
        return user;
    }

    public User selectUserByUserAuthentication(String userAuthenticationId){
        if (userAuthenticationId == null){
            return null;
        }
        UserToUserAuthentication userToUserAuthentication = userToUserAuthenticationMapper.findOneByUserAuthentication(userAuthenticationId);
        if (userToUserAuthentication == null){
            return null;
        }
        return userMapper.findOneByUserToUserAuthentication(userToUserAuthentication);
    }

    public User selectUserByUserBasicInfo(String userBasicInfoId){
        UserToUserBasicInfo userToUserBasicInfo = userToUserBasicInfoMapper.selectUserByUserBasicInfo(userBasicInfoId);
        return userMapper.selectById(userToUserBasicInfo.getUserId());
    }

    public UserBasicInfo selectUserBasicInfo(String userId){
        UserToUserBasicInfo userToUserBasicInfo = userToUserBasicInfoMapper.findOneByUser(userId);
        return userBasicInfoMapper.selectById(userToUserBasicInfo.getUserBasicInfoId());
    }

    public boolean insertUser(User user, UserBasicInfo userBasicInfo, UserAuthentication userAuthentication){
        userBasicInfoMapper.insert(userBasicInfo);
        if (userBasicInfo.getUserBasicInfoId() == null){
            return false;
        }
        userAuthenticationMapper.insert(userAuthentication);
        if (userAuthentication.getUserAuthenticationId() == null){
            return false;
        }
        userMapper.insert(user);
        if (user.getUserId() == null){
            return false;
        }
        UserToUserBasicInfo userToUserBasicInfo = new UserToUserBasicInfo(null ,user.getUserId(),userBasicInfo.getUserBasicInfoId());
        userToUserBasicInfoMapper.insert(userToUserBasicInfo);
        if (userToUserBasicInfo.getId() == null){
            return false;
        }
        UserToUserAuthentication userToUserAuthentication = new UserToUserAuthentication(null, user.getUserId(),userAuthentication.getUserAuthenticationId());
        userToUserAuthenticationMapper.insert(userToUserAuthentication);
        if (userToUserAuthentication.getId() == null){
            return false;
        }
        return true;
    }
}
