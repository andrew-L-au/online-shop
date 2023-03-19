package model.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "shop_owner")
public class ShopOwner {
    @TableId(value = "shop_owner_id",type = IdType.ASSIGN_ID)
    private Long shopOwnerId;
    @TableField(exist = false)
    private User user;
}
