package com.example.userservice.model.merchandise.connect;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ModifyMerchandiseRequestToMerchandise {
    private String id;
    private String merchandiseId;
    private String modifyMerchandiseRequestId;
}
