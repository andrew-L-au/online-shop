package com.example.userservice.controller.merchandiseDTO;

import com.example.userservice.model.merchandise.RequestRecordMerchandise;
import lombok.Data;

@Data
public class RequestModifyMerchandiseDTO {
    private String merchandiseId;
    private RequestRecordMerchandise requestRecordMerchandise;
    private String shopId;
}
