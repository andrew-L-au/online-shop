package com.example.userservice.model.merchandise.connect;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class RequestRecordMerchandiseToModifyMerchandiseRequest {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String requestRecordMerchandiseId;
    private String modifyMerchandiseRequestId;
}
