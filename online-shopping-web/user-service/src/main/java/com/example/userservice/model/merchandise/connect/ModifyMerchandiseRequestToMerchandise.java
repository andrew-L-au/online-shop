package com.example.userservice.model.merchandise.connect;

import lombok.Data;

@Data
public class ModifyMerchandiseRequestToMerchandise {
    private Long id;
    private Long merchandiseId;
    private Long modifyMerchandiseRequestId;
}
