package com.example.userservice.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.userservice.model.DTO.LoginResponse;
import com.example.userservice.model.connect.UserToUserAuthentication;
import com.example.userservice.model.connect.UserToUserBasicInfo;
import com.example.userservice.model.user.User;
import com.example.userservice.model.user.info.auth.UserAuthentication;
import com.example.userservice.model.user.info.basic.UserBasicInfo;
import com.example.userservice.repository.*;
import com.example.userservice.repository.mapper.user.connect.UserToUserBasicInfoMapper;
import com.example.userservice.repository.mapper.user.UserAuthenticationMapper;
import com.example.userservice.repository.mapper.user.UserBasicInfoMapper;
import com.example.userservice.repository.mapper.user.connect.UserToUserAuthenticationMapper;
import com.example.userservice.repository.mapper.user.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserBasicInfoMapper userBasicInfoMapper;

    @Autowired
    UserAuthenticationMapper userAuthenticationMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserToUserAuthenticationMapper userToUserAuthenticationMapper;

    @Autowired
    UserToUserBasicInfoMapper userToUserBasicInfoMapper;

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

    public Boolean userBasicInfoConflicts(UserBasicInfo userBasicInfo){
        if (userBasicInfo == null || userBasicInfo.getUsername() == null || userBasicInfo.getIdCardNumber() == null || userBasicInfo.getPhoneNumber() == null || userBasicInfo.getEmail() == null){
            return false;
        }
        String username = userBasicInfo.getUsername();
        String idCardNumber = userBasicInfo.getIdCardNumber();
        String phoneNumber = userBasicInfo.getPhoneNumber();
        String email = userBasicInfo.getEmail();
        Boolean exists = false;
        if (userBasicInfoRepository.selectUserBasicInfoByUsername(username) != null){
            exists = true;
        }
        if (userBasicInfoRepository.selectUserBasicInfoByPhoneNumber(phoneNumber) != null){
            exists = true;
        }
        if (userBasicInfoRepository.selectUserBasicInfoByIdCardNumber(idCardNumber) != null){
            exists = true;
        }
        if (userBasicInfoRepository.selectUserBasicInfoByEmail(email) != null){
            exists = true;
        }
        return exists;
    }

    public LoginResponse login(UserAuthentication userAuthentication) throws JsonProcessingException{
        if (userAuthentication == null){
            return new LoginResponse(null,false);
        }
        User user = this.findUser(userAuthentication);
        if (user == null || user.getUserId() == null){
            return new LoginResponse(null,false);
        }
        UserBasicInfo userBasicInfo = this.findUserBasicInfo(user.getUserId());
        if (userBasicInfo == null){
            return new LoginResponse(null,false);
        }
        user.setUserBasicInfo(userBasicInfo);
        user.setUserAuthentication(userAuthentication);
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        String token = JWT.create().withClaim("user",userJson).sign(Algorithm.HMAC256("1"));
        return new LoginResponse(token,true);
    }

    public Boolean registerNewUser(User user, UserBasicInfo userBasicInfo, UserAuthentication userAuthentication){
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

    public User findUser(UserAuthentication userAuthentication){
        if (userAuthentication == null){
            return null;
        }
        return userRepository.selectUser(userAuthentication.getPrincipal(),userAuthentication.getCredential());
    }

    public User findUserByPrincipal(String principal){
        if (principal == null){
            return null;
        }
        return userRepository.selectUser(principal);
    }

    public UserBasicInfo findUserBasicInfo(Long userId){
        return userRepository.selectUserBasicInfo(userId);
    }

    public User findUser(String idCardNumber){
        UserBasicInfo userBasicInfo = userBasicInfoRepository.selectUserBasicInfoByIdCardNumber(idCardNumber);
        return userRepository.selectUserByUserBasicInfo(userBasicInfo.getUserBasicInfoId());
    }

    @Transactional
    public Boolean changeUserBasicInfo(Long userId, UserBasicInfo newUserBasicInfo){
        if (userId == null || newUserBasicInfo == null){
            return false;
        }
        if (userBasicInfoConflicts(newUserBasicInfo)){
            return false;
        }
        Long originalUserBasicInfoId = userToUserBasicInfoMapper.selectUserBasicInfoByUser(userId);
        if (originalUserBasicInfoId == null){
            return false;
        }
        UserBasicInfo originalUserBasicInfo = userBasicInfoMapper.selectById(originalUserBasicInfoId);
        if (originalUserBasicInfo != null){
            newUserBasicInfo.setUserBasicInfoId(originalUserBasicInfo.getUserBasicInfoId());
            userBasicInfoMapper.updateById(newUserBasicInfo);
        }
        return true;
    }

    @Transactional
    public boolean changePassword(Long userId,String password){
        if (userId == null || password == null){
            return false;
        }
        Long userAuthenticationId = userToUserAuthenticationMapper.findUserAuthenticationByUser(userId);
        if (userAuthenticationId == null){
            return false;
        }
        UserAuthentication userAuthentication = userAuthenticationMapper.selectById(userAuthenticationId);
        if (userAuthentication == null || userAuthentication.getPrincipal() == null || userAuthentication.getCredential() == null){
            return false;
        }
        userAuthentication.setCredential(password);
        if (userAuthentication.getUserAuthenticationId() == null){
            return false;
        }
        int rowNum = userAuthenticationMapper.updateById(userAuthentication);
        if (rowNum <= 0){
            throw new RuntimeException();
        }
        return true;
    }
}
