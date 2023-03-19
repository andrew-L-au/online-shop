package model.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.user.info.auth.UserAuthentication;
import model.user.info.auth.UserRole;
import model.user.info.basic.UserBasicInfo;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user")
public class User {
    @TableId(value = "user_id",type = IdType.ASSIGN_ID)
    private Long userId;
    @TableField(exist = false)
    private UserBasicInfo userBasicInfo;
    @TableField(exist = false)
    private UserAuthentication userAuthentication;
    private UserRole userRole;
}
