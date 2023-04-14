package com.example.userservice.model.merchandise.connect;

import lombok.Data;

@Data
public class RequestRecordMerchandiseToModifyMerchandiseRequest {
    private Long id;
    private Long requestRecordMerchandiseId;
    private Long modifyMerchandiseRequestId;
}
