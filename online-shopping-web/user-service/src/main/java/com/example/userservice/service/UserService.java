package com.example.userservice.service;


import com.example.userservice.model.user.User;
import com.example.userservice.model.user.info.auth.UserAuthentication;
import com.example.userservice.model.user.info.basic.UserBasicInfo;
import com.example.userservice.repository.*;
import com.example.userservice.repository.mapper.UserAuthenticationMapper;
import com.example.userservice.repository.mapper.UserBasicInfoMapper;
import com.example.userservice.repository.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String registerNewUser(User user, UserBasicInfo userBasicInfo,UserAuthentication userAuthentication){
        boolean userNameDuplicate = existOneInfoWithSameUsername(userBasicInfo.getUsername());
        boolean phoneNumberDuplicate = existOneInfoWithSamePhoneNumber(userBasicInfo.getPhoneNumber());
        boolean idCardNumberDuplicate = existOneInfoWithSameIdCardNumber(userBasicInfo.getIdCardNumber());
        boolean emailDuplicate = existOneInfoWithSameEmail(userBasicInfo.getEmail());
        if (userNameDuplicate){
            return "user name duplicate";
        }
        if (phoneNumberDuplicate){
            return "phone number duplicate";
        }
        if (idCardNumberDuplicate){
            return "id card duplicate";
        }
        if (emailDuplicate){
            return "email duplicate";
        }
        if (existUserAuthentication(userAuthentication)){
            return "password duplicate";
        }
        if (user == null || userBasicInfo == null || userAuthentication == null){
            return "information uncompleted";
        }
        if (userBasicInfo.getUsername() == null || userBasicInfo.getIdCardNumber() == null || userBasicInfo.getUsername() == null || userBasicInfo.getUsername() == null){
            return "information uncompleted";
        }
        if (userAuthentication.getCredential() == null || userAuthentication.getPrincipal() == null){
            return "information uncompleted";
        }
        if (user.getUserRole() == null){
            return "information uncompleted";
        }
        userRepository.insertUser(user,userBasicInfo,userAuthentication);
        return "success";
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
}
