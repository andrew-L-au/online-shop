package com.example.userservice.model.merchandise.connect;

import lombok.Data;

@Data
public class RequestRecordMerchandiseToImage {
    private String id;
    private String requestRecordMerchandiseId;
    private String imageId;
}
