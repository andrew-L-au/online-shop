package com.example.userservice.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.userservice.model.connect.UserToUserBasicInfo;
import com.example.userservice.model.user.info.basic.UserBasicInfo;
import com.example.userservice.repository.mapper.UserBasicInfoMapper;
import com.example.userservice.repository.mapper.connect.UserToUserBasicInfoMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Data
public class UserBasicInfoRepository {
    @Autowired
    private UserBasicInfoMapper userBasicInfoMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserToUserBasicInfoMapper userToUserBasicInfoMapper;

    public UserBasicInfo selectUserBasicInfoByUsername(String username){
        return userBasicInfoMapper.selectOne(new QueryWrapper<UserBasicInfo>().eq("username", username));
    }

    public UserBasicInfo selectUserBasicInfoByPhoneNumber(String phoneNumber){
        return userBasicInfoMapper.selectOne(new QueryWrapper<UserBasicInfo>().eq("phone_number", phoneNumber));
    }

    public UserBasicInfo selectUserBasicInfoByIdCardNumber(String idCardNumber){
        return userBasicInfoMapper.selectOne(new QueryWrapper<UserBasicInfo>().eq("id_card_number", idCardNumber));
    }

    public UserBasicInfo selectUserBasicInfoByEmail(String email){
        return userBasicInfoMapper.selectOne(new QueryWrapper<UserBasicInfo>().eq("email", email));
    }

    public UserBasicInfo selectUserBasicInfo(Long userId){
        UserToUserBasicInfo userToUserBasicInfo = userToUserBasicInfoMapper.findOneByUser(userId);
        return userBasicInfoMapper.selectById(userToUserBasicInfo.getUserBasicInfoId());
    }

}
