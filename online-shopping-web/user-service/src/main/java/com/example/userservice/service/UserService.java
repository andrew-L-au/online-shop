package com.example.userservice.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.userservice.model.DTO.LoginResponse;
import com.example.userservice.model.user.User;
import com.example.userservice.model.user.info.auth.UserAuthentication;
import com.example.userservice.model.user.info.basic.UserBasicInfo;
import com.example.userservice.repository.*;
import com.example.userservice.repository.mapper.user.UserAuthenticationMapper;
import com.example.userservice.repository.mapper.user.UserBasicInfoMapper;
import com.example.userservice.repository.mapper.connect.UserToUserAuthenticationMapper;
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
    public boolean changePhoneNumber(Long userId,String phoneNumber){
        UserBasicInfo userBasicInfo = userBasicInfoRepository.selectUserBasicInfo(userId);
        if (userBasicInfo != null && phoneNumber != null){
            userBasicInfo.setPhoneNumber(phoneNumber);
            if (existOneInfoWithSamePhoneNumber(phoneNumber)){
                userBasicInfoRepository.getUserBasicInfoMapper().updateById(userBasicInfo);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public boolean changeUsername(Long userId,String username){
        UserBasicInfo userBasicInfo = userBasicInfoRepository.selectUserBasicInfo(userId);
        if (userBasicInfo != null && username != null){
            userBasicInfo.setUsername(username);
            if (existOneInfoWithSameUsername(username)){
                userBasicInfoRepository.getUserBasicInfoMapper().updateById(userBasicInfo);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public boolean changeEmail(Long userId,String email){
        UserBasicInfo userBasicInfo = userBasicInfoRepository.selectUserBasicInfo(userId);
        if (userBasicInfo != null && email != null){
            userBasicInfo.setEmail(email);
            if (existOneInfoWithSameEmail(email)){
                userBasicInfoRepository.getUserBasicInfoMapper().updateById(userBasicInfo);
                return true;
            }
        }
        return false;
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
