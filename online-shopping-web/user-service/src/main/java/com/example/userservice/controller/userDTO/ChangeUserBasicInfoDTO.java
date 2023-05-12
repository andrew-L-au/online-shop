package com.example.userservice.controller.userDTO;

import com.example.userservice.model.user.info.basic.UserBasicInfo;
import lombok.Data;

@Data
public class ChangeUserBasicInfoDTO {
    private String userId;
    private UserBasicInfo userBasicInfo;
}
