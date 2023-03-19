package model.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "customer")
public class Customer{
    @TableId(value = "customer_id",type = IdType.ASSIGN_ID)
    private Long customerId;
    @TableField(exist = false)
    private User user;
}
