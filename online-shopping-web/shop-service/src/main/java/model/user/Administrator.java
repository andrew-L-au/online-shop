package model.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "administrator")
public class Administrator extends User{
    @TableId(value = "administrator_id",type = IdType.ASSIGN_ID)
    private Long administratorId;
}
