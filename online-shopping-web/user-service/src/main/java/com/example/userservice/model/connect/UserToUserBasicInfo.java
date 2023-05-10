package com.example.userservice.model.connect;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_to_user_basic_info")
public class UserToUserBasicInfo {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String userId;
    private String userBasicInfoId;
}
