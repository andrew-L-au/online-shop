package com.example.userservice.controller.merchandiseDTO;

import com.example.userservice.model.merchandise.RequestRecordMerchandise;
import lombok.Data;

@Data
public class RequestNewMerchandiseDTO {
    private RequestRecordMerchandise requestRecordMerchandise;
    private Long shopId;
}
