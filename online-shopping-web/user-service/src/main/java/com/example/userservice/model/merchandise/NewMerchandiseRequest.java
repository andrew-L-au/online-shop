package com.example.userservice.model.merchandise;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.userservice.model.shop.RequestStatus;
import lombok.Data;

@Data
public class NewMerchandiseRequest {
    @TableId(value = "new_merchandise_request_id")
    private Long newMerchandiseRequestId;
    @TableField(exist = false)
    private RequestRecordMerchandise requestRecordMerchandise;
    private RequestStatus requestStatus;
}
