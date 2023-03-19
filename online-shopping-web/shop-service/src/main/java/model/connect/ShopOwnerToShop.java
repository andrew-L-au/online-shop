package model.connect;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopOwnerToShop {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long shopOwnerId;
    private Long shopId;
}
